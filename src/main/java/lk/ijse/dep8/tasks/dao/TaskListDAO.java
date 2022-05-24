package lk.ijse.dep8.tasks.dao;

import lk.ijse.dep8.tasks.dao.exception.DataAccessException;
import lk.ijse.dep8.tasks.entities.Task_list;
import lk.ijse.dep8.tasks.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskListDAO {

    private Connection connection;

    public TaskListDAO(Connection connection) {
        this.connection = connection;
    }

    public User saveTaskList(Task_list task_list) {
        try {
            if (!existsById(task_list.getId())) {

                PreparedStatement stm = connection.prepareStatement("INSERT INTO task_list (id,name,user_id) VALUES (?,?,?)");
                stm.setInt(1,task_list.getId());
                stm.setString(2,task_list.getName());
                stm.setString(3, task_list.getUserId());
                if(stm.executeUpdate()!=1){
                    throw new SQLException("Failed to save the task");
                }
            }else{
                PreparedStatement stm = connection.prepareStatement("UPDATE task_list SET name=?,user_id?WHERE id=?");
                stm.setString(1, task_list.getName());
                stm.setString(2, task_list.getUserId());
                stm.setInt(3,task_list.getId());

                if(stm.executeUpdate()!=1){
                    throw new SQLException("Failed to update the task");
                }

            }
            return task_list;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteTaskById(int id) {
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

    public Optional<Task_list> findTaskById(int task_id) {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("SELECT * FROM task_list WHERE id=?");
            stm.setInt(1,task_id);
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

    public boolean existsById(int taskId) {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("SELECT id FROM  task_list WHERE id=?");
            stm.setInt(1, taskId);
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Task_list> findAllTasks() {


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

    }
}
