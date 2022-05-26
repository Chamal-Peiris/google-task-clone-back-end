package lk.ijse.dep8.tasks.dao.custom.impl;

import lk.ijse.dep8.tasks.dao.custom.TaskListDAO;
import lk.ijse.dep8.tasks.entities.Task_list;

import java.util.List;
import java.util.Optional;

public class TaskListDAOImpl implements TaskListDAO {
    @Override
    public boolean existById(Integer pk) {
        return false;
    }

    @Override
    public Task_list save(Task_list entity) {
        return null;
    }

    @Override
    public void deleteById(Integer pk) {

    }

    @Override
    public Optional<Task_list> findById(Integer pk) {
        return Optional.empty();
    }

    @Override
    public List<Task_list> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
/*
    private Connection connection;

    public TaskListDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object save(Object entity) {
        Task_list task_list= (Task_list) entity;
        try {
            if (!existById(taskList.getId())) {

                PreparedStatement stm = connection.prepareStatement("INSERT INTO task_list (id,name,user_id) VALUES (?,?,?)");
                stm.setInt(1,taskList.getId());
                stm.setString(2,taskList.getName());
                stm.setString(3, taskList.getUserId());
                if(stm.executeUpdate()!=1){
                    throw new SQLException("Failed to save the task");
                }
            }else{
                PreparedStatement stm = connection.prepareStatement("UPDATE task_list SET name=?,user_id?WHERE id=?");
                stm.setString(1, taskList.getName());
                stm.setString(2, taskList.getUserId());
                stm.setInt(3,taskList.getId());

                if(stm.executeUpdate()!=1){
                    throw new SQLException("Failed to update the task");
                }

            }
            return taskList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Object id) {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("DELETE FROM task_list WHERE id =?");
            stm.setInt(1,id);
            if(stm.executeUpdate()!=1){
                throw new DataAccessException("No User Found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public Optional<Object> findById(Object taskId) {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("SELECT * FROM task_list WHERE id=?");
            stm.setInt(1,taskId);
            ResultSet rst = stm.executeQuery();
            if(rst.next()){
                return Optional.of(new Task_list(rst.getInt("id"), rst.getString("name"), rst.getString("user_id") ));

            }else{
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean existById(Object taskId) {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("SELECT id FROM  task_list WHERE id=?");
            stm.setInt(1, (Integer) taskId);
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Object> findAll() {


        try {
            Statement statement = connection.createStatement();
            ResultSet rst = statement.executeQuery("SELECT * FROM task_list");
            List<Task_list> taskLists=new ArrayList<>();
            while(rst.next()){
                taskLists.add(new Task_list(rst.getInt("id"),rst.getString("name"), rst.getString("user_id")));
            }
            return taskLists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public long count() {
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT COUNT(id) AS count FROM tast_list");
            if (rst.next()){
                return rst.getLong("count");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/
}
