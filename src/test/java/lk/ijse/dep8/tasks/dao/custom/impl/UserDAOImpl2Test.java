package lk.ijse.dep8.tasks.dao.custom.impl;

import lk.ijse.dep8.tasks.dao.impl.UserDAOImpl;
import lk.ijse.dep8.tasks.entities.User;
import lk.ijse.dep8.tasks.service.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserDAOImpl2Test {

    private UserDAOImpl userDAO;
    private Session session;

    @BeforeEach
    void setup() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        userDAO = new UserDAOImpl(session);

    }

    @AfterEach
    void tearDown() {
        session.getTransaction().commit();
        session.close();
    }

    @Order(2)
    @Test
    void existById() {

        boolean u001 = userDAO.existById("U001");
        assertTrue(u001);

    }


    @Order(1)
    @Test
    void save() {
        //givem
        User givenUser = new User("U001", "Chamal.peirs", "123", "chamal", null);
        User givenUser1 = new User("U002", "lahiru.com", "123", "lajiru", null);
        User givenUser2 = new User("U003", "dulanga.com", "123", "dulanga", null);

        //when
        User actualUser = userDAO.save(givenUser);
        User actualUser1 = userDAO.save(givenUser1);
        User actualUser2 = userDAO.save(givenUser2);
        //then

        assertEquals(givenUser, actualUser);
        assertEquals(givenUser1, actualUser1);
        assertEquals(givenUser2, actualUser2);


    }

    @Order(3)
    @Test
    void deleteById() {
        existById();

    }

    @Order(4)
    @Test
    void findById() {
        // given

        User givenUser = new User("U001", "Chamal.peirs", "123", "chamal", null);

        //when
        Optional<User> userFrom = userDAO.findById("U001");

        assertEquals(givenUser,userFrom.get());
    }

    @Order(5)
    @Test
    void findAll() {

        List<User> all = userDAO.findAll();
        assertNotNull(all);

    }

    @Order(6)
    @Test
    void count() {
        long count = userDAO.count();


    }

    @Order(7)
    @Test
    void existsUserByEmailOrId() {
        boolean u001 = userDAO.existsUserByEmailOrId("U001");
        assertTrue(u001);
    }

    @Order(8)
    @Test
    void findUserByIdOrEmail() {
        User givenUser = new User("U001", "Chamal.peirs", "123", "chamal", null);
        Optional<User> u001 = userDAO.findUserByIdOrEmail("U001");
        assertEquals(givenUser,u001.get());
    }
}