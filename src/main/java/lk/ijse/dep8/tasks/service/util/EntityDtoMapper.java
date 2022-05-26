package lk.ijse.dep8.tasks.service.util;

import lk.ijse.dep8.tasks.dto.TaskDTO;
import lk.ijse.dep8.tasks.dto.TaskListDTO;
import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.entities.Task;
import lk.ijse.dep8.tasks.entities.Task_list;
import lk.ijse.dep8.tasks.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class EntityDtoMapper {

    public static UserDTO getUserDTO(User user) {
        ModelMapper mapper = new ModelMapper();
        return mapper.typeMap(User.class, UserDTO.class)
                .addMapping(User::getProfilePic, UserDTO::setPicture)
                .map(user);
    }

    public static TaskListDTO getTaskListDTO(Task_list taskList) {
        ModelMapper mapper = new ModelMapper();
        return mapper.typeMap(Task_list.class, TaskListDTO.class)
                .addMapping(Task_list::getName, TaskListDTO::setTitle)
                .map(taskList);
    }

    public static TaskDTO getTaskDTO(Task task) {
        ModelMapper mapper = new ModelMapper();
        return mapper.typeMap(Task.class, TaskDTO.class)
                .addMapping(Task::getDetails, TaskDTO::setNotes)
                .map(task);
    }

    public static User getUser(UserDTO userDTO){
        ModelMapper mapper = new ModelMapper();
        return mapper.typeMap(UserDTO.class, User.class)
                .addMapping(UserDTO::getPicture, User::setProfilePic)
                .map(userDTO);
    }

    public static Task_list getTaskList(TaskListDTO taskListDTO){
        ModelMapper mapper = new ModelMapper();
        return mapper.typeMap(TaskListDTO.class, Task_list.class)
                .addMapping(TaskListDTO::getTitle, Task_list::setName)
                .map(taskListDTO);
    }

    public static Task getTask(TaskDTO taskDTO){
        ModelMapper mapper = new ModelMapper();
        return mapper.typeMap(TaskDTO.class, Task.class)
                .addMapping(TaskDTO::getNotes, Task::setDetails)
                .map(taskDTO);
    }

}
