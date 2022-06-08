package lk.ijse.dep8.tasks.service.custom.impl;

import lk.ijse.dep8.tasks.dao.DaoFactory;
import lk.ijse.dep8.tasks.dao.SuperDAO;
import lk.ijse.dep8.tasks.dao.custom.QueryDAO;
import lk.ijse.dep8.tasks.dao.custom.TaskDAO;
import lk.ijse.dep8.tasks.dao.custom.TaskListDAO;
import lk.ijse.dep8.tasks.dao.custom.impl.TaskDAOImpl;
import lk.ijse.dep8.tasks.dto.TaskDTO;
import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.entities.Task;
import lk.ijse.dep8.tasks.service.custom.TaskService;
import lk.ijse.dep8.tasks.service.exception.FailedExecutionException;
import lk.ijse.dep8.tasks.service.util.EntityDtoMapper;
import lk.ijse.dep8.tasks.service.util.ExecutionContext;
import lk.ijse.dep8.tasks.util.JNDIConnectionPool;
import lk.ijse.dep8.tasks.util.ResponseStatusException;

import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {
    private DataSource dataSource;

    public TaskServiceImpl() {
        dataSource = JNDIConnectionPool.getInstance().getDataSource();
    }

    @Override
    public boolean existsTask(String emailOrId) {
        return false;
    }

    @Override
    public TaskDTO saveTask(int taskListId, String userId, TaskDTO task) {
        try {
            Connection connection = dataSource.getConnection();
            TaskListDAO taskListDAO = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.TASK_LIST);
            boolean isExists = taskListDAO.existTaskListByIdAndUserId(taskListId, userId);
            if (isExists){
                throw new FailedExecutionException("Invalid user id or Task list id");
            }
            if (task == null || task.getTitle().trim().isEmpty()) {
                throw new FailedExecutionException("Invalid title or title is empty");
            }
            connection.setAutoCommit(false);
            pushDown(connection, 0, taskListId);
            TaskDAO taskDAO = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.TASK);
            Task taskEntity = EntityDtoMapper.getTask(task);
            Task save = taskDAO.save(taskEntity);
            connection.commit();
            TaskDTO taskDTO = EntityDtoMapper.getTaskDTO(save);
            return taskDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<List<TaskDTO>> getAllTasks(int taskListId, String userId) {
        try (Connection connection = dataSource.getConnection()) {
            TaskListDAO taskListDAOImpl = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.TASK_LIST);
            if (!taskListDAOImpl.existTaskListByIdAndUserId(taskListId, userId)) {
                throw new FailedExecutionException("TaskList is not exists!");
            }
            TaskDAO taskDAOImpl = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.TASK);
            Optional<List<Task>> list = taskDAOImpl.findByTaskListId(taskListId);
            if (list.isPresent()) {
                List<TaskDTO> taskDTOs = new ArrayList<>();
                List<Task> tasks = list.get();
                for (Task task : tasks) {
                    TaskDTO taskDTO = EntityDtoMapper.getTaskDTO(task);
                    taskDTOs.add(taskDTO);
                }
                return Optional.of(taskDTOs);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new FailedExecutionException("Failed to get the Task", e);
        }
    }
    @Override
    public Optional<TaskDTO> getSpecificTask(int taskListId, String userId, int taskId) {
        Connection connection=null;
        try {
            connection = dataSource.getConnection();
            QueryDAO queryDAO = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.QUERY_DAO);
            Task task = (Task) queryDAO.getTask(taskId, taskListId, userId);
            return Optional.of(EntityDtoMapper.getTaskDTO(task));
        } catch (SQLException e) {
            throw new FailedExecutionException("Failed to delete the task",e);
        }
    }

    @Override
    public void deleteTask(String userId,int taskListId,int taskId) {
        Connection connection=null;
        try {
            connection = dataSource.getConnection();
            QueryDAO queryDAO = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.QUERY_DAO);
            Task task = (Task) queryDAO.getTask(taskId, taskListId, userId);
            connection.setAutoCommit(false);
            pushUp(connection,task.getPosition(),task.getTaskListId());
            TaskDAO taskDAO = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.TASK);
            taskDAO.deleteById(task.getId());
            connection.commit();
        } catch (SQLException e) {
            throw new FailedExecutionException("Failed to delete the task",e);
        }finally {
            Connection tempConnection=connection;
            ExecutionContext.execute(()->{
                tempConnection.setAutoCommit(true);
                tempConnection.rollback();
            });
        }
    }

    @Override
    public void updateTask(String userId, int taskListId, int taskId, TaskDTO newTask) {
        Connection connection=null;
        try {
            connection = dataSource.getConnection();
            QueryDAO queryDAO = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.QUERY_DAO);
            Task oldTask = (Task) queryDAO.getTask(taskId, taskListId, userId);

            if (newTask.getTitle() == null || newTask.getTitle().trim().isEmpty()) {
                throw new ResponseStatusException(400, "Invalid title or title is empty");
            } else if (newTask.getPosition() == null || newTask.getPosition() < 0) {
                throw new ResponseStatusException(400, "Invalid position or position value is empty");
            }

            connection.setAutoCommit(false);
            if (oldTask.getPosition() != (newTask.getPosition())) {
                pushUp(connection, oldTask.getPosition(), oldTask.getTaskListId());
                pushDown(connection, newTask.getPosition(), oldTask.getTaskListId());
            }
            TaskDAO taskDAO = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.TASK);
            Task task = EntityDtoMapper.getTask(newTask);
            taskDAO.save(task);
            connection.commit();

        } catch (SQLException e) {
            throw new FailedExecutionException("Failed to get the TaskList");
        } finally {
            Connection tempConnection=connection;
            ExecutionContext.execute(()->{
                if (tempConnection!=null && !tempConnection.getAutoCommit()) {
                    tempConnection.rollback();
                    tempConnection.setAutoCommit(true);
                }
                tempConnection.close();
            });
        }
    }


    /*================================================================================*/

    @Override
    public UserDTO saveTaskList(Part picture, String appLocation, UserDTO user) {
        return null;
    }

    @Override
    public UserDTO getTaskList(int taskListId, String userId) {
        return null;
    }

    @Override
    public void deleteTaskList(String id, String appLocation) {

    }

    @Override
    public void updateTaskList(UserDTO user, Part picture, String appLocation) {

    }

    @Override
    public void pushUp(Connection connection, int pos, int taskListId) throws SQLException {

        TaskDAO daoImpl = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.TASK);
        daoImpl.pushUp(connection,pos,taskListId);
    }

    @Override
    public void pushDown(Connection connection, int pos, int taskListId) throws SQLException {
        TaskDAO daoImpl = DaoFactory.getInstance().getDao(connection, DaoFactory.DAOTypes.TASK);
        daoImpl.pushDown(connection,pos,taskListId);
    }
}
