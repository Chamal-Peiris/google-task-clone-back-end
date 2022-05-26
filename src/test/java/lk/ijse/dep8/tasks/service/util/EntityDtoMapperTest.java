package lk.ijse.dep8.tasks.service.util;

import lk.ijse.dep8.tasks.dto.TaskDTO;
import lk.ijse.dep8.tasks.dto.TaskListDTO;
import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.entities.Task;
import lk.ijse.dep8.tasks.entities.TaskList;
import lk.ijse.dep8.tasks.entities.User;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityDtoMapperTest {

    @Test
    void getUserDTO() {



    }


    @Test
    void testGetUserDTO() {
        //given
        User user = new User("C001", "Chamal.peiris", "123", "1236", "pic");
        //when
        UserDTO userDTO = EntityDtoMapper.getUserDTO(user);

        assertEquals(user.getId(),userDTO.getId());
        assertEquals(user.getFullName(),userDTO.getName());
        assertEquals(user.getProfilePic(),user.getProfilePic());
    }
    @RepeatedTest(10)
    @Test
    void getTaskListDTO() {
        TaskList task_list = new TaskList(1, "Hii", "C001");
        TaskListDTO taskListDTO = EntityDtoMapper.getTaskListDTO(task_list);
        System.out.println(taskListDTO);
        System.out.println(task_list);
        assertEquals(taskListDTO.getTitle(),task_list.getName());



    }

   // @RepeatedTest(10)
    @Test
    void getTaskDTO() {
        Task task = new Task(1, "Hiii", "ddf", 10, Task.Status.needsAction, 3);
        TaskDTO taskDTO = EntityDtoMapper.getTaskDTO(task);
        assertEquals(task.getPosition(),taskDTO.getPosition());
        assertEquals(task.getStatus(),taskDTO.getStatus());

    }
}