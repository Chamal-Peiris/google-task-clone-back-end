package lk.ijse.dep8.tasks.dao.impl;

import lk.ijse.dep8.tasks.dao.custom.TaskDAO;
import lk.ijse.dep8.tasks.dao.exception.DataAccessException;
import lk.ijse.dep8.tasks.entities.Task;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAOImpl extends CrudDAOImpl<Task, Integer> implements TaskDAO  {
    //private Session session;

    public TaskDAOImpl(Session session) {
        this.session = session;
    }

}
