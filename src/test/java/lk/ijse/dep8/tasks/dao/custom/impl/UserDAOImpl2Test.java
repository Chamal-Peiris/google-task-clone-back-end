package lk.ijse.dep8.tasks.dao.custom.impl;

import com.sun.org.apache.xml.internal.utils.res.XResources_de;
import lk.ijse.dep8.tasks.entities.User;
import lk.ijse.dep8.tasks.service.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImpl2Test {

    private UserDAOImpl2 userDAO;
    private Session session;

    @BeforeEach
    void setup() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        userDAO = new UserDAOImpl2(session);

    }

    @AfterEach
    void tearDown() {
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void existById() {

        boolean u001 = userDAO.existById("U001");
        assertTrue(u001);

    }

    @Test
    void save() {
        //givem
        User givenUser = new User("U001", "Chamal.peirs", "123", "chamal", null);

        //when
        User actualUser = userDAO.save(givenUser);
        //then

        assertEquals(givenUser, actualUser);


    }

    @Test
    void deleteById() {


    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void count() {
    }

    @Test
    void existsUserByEmailOrId() {
    }

    @Test
    void findUserByIdOrEmail() {
    }
}