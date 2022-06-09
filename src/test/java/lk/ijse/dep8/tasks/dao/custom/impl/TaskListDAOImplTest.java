package lk.ijse.dep8.tasks.dao.custom.impl;

import lk.ijse.dep8.tasks.entities.TaskList;
import lk.ijse.dep8.tasks.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListDAOImplTest {


    @Test
    void existById() {
    }

    @Test
    void save() {
        User user = new User("U001", "chamalpeiris3g@gmail.com", "123", "chamal", null);
        new TaskList(1,"Homework",user);

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
    void existTaskListByIdAndUserId() {
    }

    @Test
    void getTaskListByIdAndUserId() {
    }

    @Test
    void findByUserId() {
    }
}