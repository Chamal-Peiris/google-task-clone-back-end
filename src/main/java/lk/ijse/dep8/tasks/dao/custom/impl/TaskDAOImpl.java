package lk.ijse.dep8.tasks.dao.custom.impl;

import lk.ijse.dep8.tasks.dao.custom.TaskDAO;
import lk.ijse.dep8.tasks.entities.Task;

import java.util.List;
import java.util.Optional;

public class TaskDAOImpl implements TaskDAO  {
    @Override
    public boolean existById(Integer pk) {
        return false;
    }

    @Override
    public Task save(Task entity) {
        return null;
    }

    @Override
    public void deleteById(Integer pk) {

    }

    @Override
    public Optional<Task> findById(Integer pk) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
  /*  private Connection connection;


    public TaskDAOImpl(Connection connection){
        this.connection=connection;
    }
    @Override
    public Object save(Object task){
        try {
            if (!existsTaskById(task.getId())){
                PreparedStatement stm = connection.prepareStatement("INSERT INTO task (title, details, position, status, task_list_id) VALUES (?,?,?,?,?)");
                stm.setString(1,task.getTitle());
                stm.setString(2,task.getDetails());
                stm.setInt(3,task.getPosition());
                stm.setString(4,task.getStatus().toString());
                stm.setInt(5,task.getTaskListId());
                if (stm.executeUpdate()!=1){
                    throw new SQLException("Failed to save the user");
                }
            }else {
                PreparedStatement stm = connection.prepareStatement("UPDATE task SET title=?, details =?, position=?, status=?, task_list_id=? WHERE id=?");
                stm.setString(1,task.getTitle());
                stm.setString(2,task.getDetails());
                stm.setInt(3,task.getPosition());
                stm.setString(4,task.getStatus().toString());
                stm.setInt(5,task.getTaskListId());
                stm.setInt(6,task.getId());
                if (stm.executeUpdate()!=1){
                    throw new SQLException("Failed to update the user");
                }
            }
            return task;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteById(Object taskId){
        try {
            if (!existsTaskById(taskId)){
                throw new DataAccessException("No user Found!");
            }
            PreparedStatement stm = connection.prepareStatement("DELETE FROM task WHERE id=?");
            stm.setInt(1,taskId);
            if (stm.executeUpdate()!=1){
                throw new SQLException("Failed to delete the user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Object> findById(Object taskId){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM task WHERE id=?");
            stm.setInt(1,taskId);
            ResultSet rst = stm.executeQuery();
            if (rst.next()){
                return Optional.of(new Task(rst.getInt("id"),
                        rst.getString("title"),
                        rst.getString("details"),
                        rst.getInt("position"),
                        Task.Status.valueOf(rst.getString("status")),
                        rst.getInt("task_list_id")));
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existById(Object taskId){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM task WHERE id=?");
            stm.setInt(1,taskId);
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> findAllTasks(Object taskId){
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM task");
            List<Task> tasks = new ArrayList<>();
            while (rst.next()){
                tasks.add(new Task(rst.getInt("id"),
                        rst.getString("title"),
                        rst.getString("details"),
                        rst.getInt("position"),
                        Task.Status.valueOf(rst.getString("status")),
                        rst.getInt("task_list_id")));
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long countTasks(){
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT COUNT(*) AS count FROM task");
            if (rst.next()){
                return rst.getLong("count");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/
}
