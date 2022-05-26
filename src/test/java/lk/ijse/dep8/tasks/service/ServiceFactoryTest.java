package lk.ijse.dep8.tasks.service;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ServiceFactoryTest {

    @Test
    void getInstance() {
        ServiceFactory isntance1 = ServiceFactory.getInstance();
        ServiceFactory isntance2 = ServiceFactory.getInstance();
        assertEquals(isntance1,isntance2);
    }

    @Test
    void getService() {
        Connection mockConnetion=mock(Connection.class);
        SuperSevice userService=ServiceFactory.getInstance().getService(mockConnetion, ServiceFactory.ServiceTypes.USER);
        SuperSevice taskService=ServiceFactory.getInstance().getService(mockConnetion, ServiceFactory.ServiceTypes.TASK);

    }
}