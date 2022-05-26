package lk.ijse.dep8.tasks.dao.custom.impl;

import lk.ijse.dep8.tasks.dao.custom.QueryDAO;

import java.sql.Connection;

public class QueryDAOImpl extends QueryDAO {
    private Connection connection;


    public QueryDAOImpl(Connection connection){
        this.connection=connection;
    }
}
