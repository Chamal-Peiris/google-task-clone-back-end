package lk.ijse.dep8.tasks.dao.custom;

import lk.ijse.dep8.tasks.dao.CrudDao;
import lk.ijse.dep8.tasks.entities.TaskList;

import java.util.List;
import java.util.Optional;

public interface TaskListDAO extends CrudDao<TaskList,Integer> {

    boolean existTaskListByIdAndUserId(int taskListId,String userId);
    Optional<TaskList> getTaskListByIdAndUserId(int taskListId, String userId);
    Optional<List<TaskList>> findByUserId(String userId);

}
