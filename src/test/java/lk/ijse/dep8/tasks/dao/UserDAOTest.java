package lk.ijse.dep8.tasks.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection ;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
private  Connection connection;

@BeforeEach
void setup(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dep8_task","root","12345678");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}

    @Test
    void existUser() throws SQLException {
        boolean result = UserDAO.existUser(connection, "chamal.peiris.3g@gmail.com");
        assertTrue(result);
    }
}