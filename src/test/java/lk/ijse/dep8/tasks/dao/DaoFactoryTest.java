package lk.ijse.dep8.tasks.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class DaoFactoryTest {

    @Test
    void getDao() {
        Connection mockConection=mock(Connection.class);
        UserDAO userDao = DaoFactory.getInstance().getDao(mockConection, DaoFactory.DAOTypes.USER);
        TaskDAO taskDao= DaoFactory.getInstance().getDao(mockConection, DaoFactory.DAOTypes.TASK);
        TaskListDAO taskListDao = DaoFactory.getInstance().getDao(mockConection, DaoFactory.DAOTypes.TASK_LIST);
        QueryDAO queryDao = DaoFactory.getInstance().getDao(mockConection, DaoFactory.DAOTypes.QUERY_DAO);

    }
}