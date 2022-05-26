package lk.ijse.dep8.tasks.service.custom.impl;

import lk.ijse.dep8.tasks.service.custom.UserService;

import java.sql.Connection;

public class TaskServiceImpl implements UserService {
    private Connection connection;
    public TaskServiceImpl(Connection connection){
        this.connection=connection;
    }
}
