package lk.ijse.dep8.tasks.service;

import lk.ijse.dep8.tasks.service.custom.impl.TaskServiceImpl;
import lk.ijse.dep8.tasks.service.custom.impl.UserServiceImpl;

import java.io.Serializable;
import java.sql.Connection;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){

    }

    public static ServiceFactory getInstance(){
        return (serviceFactory==null)?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperSevice>T getService(Connection connection,ServiceTypes serviceType){
        switch (serviceType){
            case TASK:
                return (T) new  UserServiceImpl();
            case USER:
                return (T) new  TaskServiceImpl();
            default:
                return null;
        }
    }

    public enum ServiceTypes{
        USER,TASK
    }
}
