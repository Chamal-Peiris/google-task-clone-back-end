package lk.ijse.dep8.tasks.dao;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDAOTest {
//    private static Connection connection;
//
//    private static UserDAOImpl userDAO;
//
//    @AfterAll
//    static void setUp() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep8_task", "root", "12345678");
//            connection.setAutoCommit(false);
//            userDAO = new UserDAOImpl(connection);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @AfterAll
//    static void tearDown() {
//        try {
//            connection.rollback();
//            connection.setAutoCommit(true);
//            connection.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    static List<User> getDummyUsers(){
//        List<User> dummies = new ArrayList<>();
//        dummies.add(new User("U001", "u001@gmail.com", "admin", "Kasun", "picture1"));
//        dummies.add(new User("U002", "u002@gmail.com", "admin", "Nuwan", "picture1"));
//        dummies.add(new User("U003", "u003@gmail.com", "admin", "Ruwan", null));
//        dummies.add(new User("U004", "u004@gmail.com", "admin", "Supun", null));
//        dummies.add(new User("U005", "u005@gmail.com", "admin", "Gayal", "picture1"));
//        return dummies;
//    }
//    @Order(1)
//    @MethodSource("getDummyUsers")
//    @ParameterizedTest
//    void saveUser(User givenUser) {
//        // when
//        User savedUser = userDAO.saveUser(givenUser);
//
//        // then
//        assertEquals(givenUser, savedUser);
//    }
//    @Order(2)
//    @ValueSource(strings = {"U001", "U002", "U100"})
//    @ParameterizedTest
//    void existsById(String givenUserId) {
//        // when
//        boolean result = userDAO.existsById(givenUserId);
//
//        // then
//        if (givenUserId.equals("U100")) {
//            assertFalse(result);
//        } else {
//            assertTrue(result);
//        }
//    }
//    @Order(3)
//    @ValueSource(strings = {"U001", "U002", "U100"})
//    @ParameterizedTest
//    void findUserById(String givenUserId) {
//        // when
//        Optional<User> userWrapper = userDAO.findUserById(givenUserId);
//
//        // then
//        if (givenUserId.equals("U100")) {
//            assertFalse(userWrapper.isPresent());
//        } else {
//            assertTrue(userWrapper.isPresent());
//        }
//    }
//
//    @Order(4)
//    @Test
//    void findAllUsers() {
//        // when
//        List<User> allUsers = userDAO.findAllUsers();
//
//        // then
//        assertTrue(allUsers.size() >= 5);
//    }
//
//    @Order(5)
//    @ValueSource(strings = {"U001", "U002", "U100"})
//    @ParameterizedTest
//    void deleteUserById(String givenUserId) {
//        // when
//        if (givenUserId.equals("U100")) {
//            assertThrows(DataAccessException.class, () -> userDAO.deleteUserById(givenUserId));
//        } else {
//            userDAO.deleteUserById(givenUserId);
//        }
//
//        assertFalse(userDAO.existsById(givenUserId));
//    }
//
//
//    @Order(6)
//    @Test
//    void count() {
//        assertTrue(userDAO.count() >= 5);
//    }
}