package lk.ijse.dep8.tasks.dao;

import lk.ijse.dep8.tasks.dto.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private Connection connection;

    @BeforeEach
    void setup() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep8_task", "root", "12345678");
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void existUser() throws SQLException {
        boolean result = UserDAO.existUser(connection, "chamal.peiris.3g@gmail.com");
        assertTrue(result);
    }

    @Test
    void saveUser() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        UserDTO userDTO = new UserDTO(uuid, "chamal.peiris3g@gmail.com", "12346", "Chamal Peiris", null);
        UserDTO userDTO1 = UserDAO.saveUser(connection, userDTO);
        assertEquals(userDTO1, userDTO);
        boolean result = UserDAO.existUser(connection, userDTO1.getEmail());
        assertTrue(result);


    }

    @ParameterizedTest
    @ValueSource(strings = {"chamal.peiris3g@gmail.com","25c00291-9a6c-4bd0-bc33-b97012faf741"})
    void testExistUser(String arg) throws SQLException {
        boolean result = UserDAO.existUser(connection, arg);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"chamal.peiris.3g@gmail.com","25c00291-9a6c-4bd0-bc33-b97012faf741"})
    void getUser(/*Given*/String arg) throws SQLException {
        //When
        UserDTO user = UserDAO.getUser(connection, arg);

        //Then
        assertNotNull(user);
    }

    @Test
    void deleteUser() throws SQLException {
        //given
        String userId="25c00291-9a6c-4bd0-bc33-b97012faf741";
        //when
        UserDAO.deleteUser(connection,userId);
        //then
        UserDAO.existUser(connection,userId);
    }

    @Test
    void updateUser() throws SQLException {
        //given
        UserDTO user = UserDAO.getUser(connection, "25c00291-9a6c-4bd0-bc33-b97012faf741");
        user.setName("Dulanga");
        //when
        UserDAO.updateUser(connection,user);

        //then
        UserDTO updatedUser = UserDAO.getUser(connection, "25c00291-9a6c-4bd0-bc33-b97012faf741");
        assertEquals(user.getName(),updatedUser.getName());
    }
}