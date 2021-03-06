package lk.ijse.dep8.tasks.dao;

import lk.ijse.dep8.tasks.dao.impl.QueryDAOImpl;
import lk.ijse.dep8.tasks.dao.impl.TaskDAOImpl;
import lk.ijse.dep8.tasks.dao.impl.TaskListDAOImpl;
import lk.ijse.dep8.tasks.dao.impl.UserDAOImpl;
import org.hibernate.Session;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return (daoFactory!=null)?(daoFactory=new DAOFactory()):daoFactory;
    }
    public <T extends SuperDAO>T getDAO(Session session, DAOTypes daoType){
        switch (daoType){
            case USER:
                return (T) new UserDAOImpl(session);
            case TASK_LIST:
                return (T) new TaskListDAOImpl(session);
            case TASK:
                return (T) new TaskDAOImpl(session);
            case QUERY_DAO:
                return (T) new QueryDAOImpl(session);
            default:
             return    null;
        }
    }

    public enum DAOTypes{
        USER,TASK_LIST,TASK,QUERY_DAO;
    }

   /* public UserDAO getUserDao(Connection connection){
        return new UserDAOImpl(connection);
    }
    public TaskListDAO getTaskListDao(Connection connection){
        return new TaskListDAOImpl();
    }
    public TaskDAO getTaskDao(Connection connection){
        return new TaskDAOImpl(connection);
    }
    public QueryDAO getQueryDao(Connection connection){
        return new QueryDAOImpl(connection);
    }*/

}
