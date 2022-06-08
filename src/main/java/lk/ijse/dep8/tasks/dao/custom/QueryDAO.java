package lk.ijse.dep8.tasks.dao.custom;

import lk.ijse.dep8.tasks.dao.SuperDAO;
import lk.ijse.dep8.tasks.entities.SuperEntity;

public interface QueryDAO<T extends SuperEntity> extends SuperDAO {
    T getTask(int taskId,int taskListId,String userId);
}
