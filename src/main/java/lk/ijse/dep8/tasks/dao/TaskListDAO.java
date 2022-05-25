package lk.ijse.dep8.tasks.dao;

import lk.ijse.dep8.tasks.entities.Task_list;

import java.util.List;
import java.util.Optional;

public interface TaskListDAO {
    public Task_list saveTaskList(Task_list taskList);
    public void deleteTaskById(int id);
    public Optional<Task_list> findTaskById(int taskId);
    public boolean existsById(int taskId);
    public List<Task_list> findAllTasks();
    public long count();


}
