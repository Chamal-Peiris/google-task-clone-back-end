package lk.ijse.dep8.tasks.dao.impl;

import lk.ijse.dep8.tasks.dao.custom.QueryDAO;
import lk.ijse.dep8.tasks.dao.exception.DataAccessException;
import lk.ijse.dep8.tasks.entities.SuperEntity;
import lk.ijse.dep8.tasks.entities.Task;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    private final Session session;
    public QueryDAOImpl(Session session) {
        this.session = session;
    }

//    @Override
//    public SuperEntity getTask(int taskId, int taskListId, String userId) {
//        try {
//            PreparedStatement stm = connection.
//                    prepareStatement("SELECT * FROM task_list tl INNER JOIN task t ON t.task_list_id = tl.id WHERE t.id=? AND tl.id=? AND tl.user_id=?");
//            stm.setInt(1, taskId);
//            stm.setInt(2, taskListId);
//            stm.setString(3, userId);
//            ResultSet rst = stm.executeQuery();
//            if (rst.next()) {
//                String title = rst.getString("title");
//                String details = rst.getString("details");
//                int position = rst.getInt("position");
//                String status = rst.getString("status");
//                return new Task(taskId, title, details,position, Task.Status.valueOf(status), taskListId);
//            } else {
//                throw new DataAccessException("Invalid user id or Task list id");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (DataAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
