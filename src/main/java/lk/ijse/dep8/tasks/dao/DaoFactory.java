package lk.ijse.dep8.tasks.dao;

import java.sql.Connection;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return (daoFactory!=null)?(daoFactory=new DaoFactory()):daoFactory;
    }
    public <T extends SuperDAO>T getDao(Connection connection, DAOTypes daoType){
        switch (daoType){
            case USER:
              //  return (T) new UserDAOImpl(connection);
            case TASK_LIST:
              //  return (T) new TaskListDAOImpl(connection);
            case TASK:
                //return (T) new TaskDAOImpl(connection);
            case QUERY_DAO:
               // return (T) new QueryDAOImpl(connection);
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
