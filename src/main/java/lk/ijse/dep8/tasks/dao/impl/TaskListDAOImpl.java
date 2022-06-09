package lk.ijse.dep8.tasks.dao.impl;

import lk.ijse.dep8.tasks.dao.CrudDao;
import lk.ijse.dep8.tasks.dao.custom.TaskListDAO;
import lk.ijse.dep8.tasks.entities.TaskList;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class TaskListDAOImpl extends CrudDAOImpl<TaskList,Integer> implements TaskListDAO {

    private final Session session;

    public TaskListDAOImpl(Session session){
        this.session=session;
    }


}
