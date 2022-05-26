package lk.ijse.dep8.tasks.service.custom.impl;

import lk.ijse.dep8.tasks.service.custom.TaskService;
import lk.ijse.dep8.tasks.service.custom.UserService;
import lk.ijse.dep8.tasks.util.JNDIConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;

public class TaskServiceImpl implements TaskService {
    private DataSource dataSource;

    public TaskServiceImpl(DataSource dataSource) {
        this.dataSource = JNDIConnectionPool.getInstance().getDataSource();
    }

}
