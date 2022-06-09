package lk.ijse.dep8.tasks.dao;

import lk.ijse.dep8.tasks.dao.custom.QueryDAO;
import lk.ijse.dep8.tasks.dao.custom.TaskDAO;
import lk.ijse.dep8.tasks.dao.custom.TaskListDAO;
import lk.ijse.dep8.tasks.dao.custom.UserDAO;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.mockito.Mockito.*;
class DaoFactoryTest {

    @Test
    void getDao() {
        Connection mockConection=mock(Connection.class);
        UserDAO userDao = DAOFactory.getInstance().getDAO(mockConection, DAOFactory.DAOTypes.USER);
        TaskDAO taskDao= DAOFactory.getInstance().getDAO(mockConection, DAOFactory.DAOTypes.TASK);
        TaskListDAO taskListDao = DAOFactory.getInstance().getDAO(mockConection, DAOFactory.DAOTypes.TASK_LIST);
        QueryDAO queryDao = DAOFactory.getInstance().getDAO(mockConection, DAOFactory.DAOTypes.QUERY_DAO);

    }
}