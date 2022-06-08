package lk.ijse.dep8.tasks.dao.custom;

import lk.ijse.dep8.tasks.dao.CrudDao;
import lk.ijse.dep8.tasks.entities.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TaskDAO extends CrudDao<Task,Integer> {

    Optional<List<Task>> findByTaskListId(Integer taskListId);
    void pushUp(Connection connection, int pos, int taskListId) throws SQLException;
    void pushDown(Connection connection, int pos, int taskListId) throws SQLException;

}
