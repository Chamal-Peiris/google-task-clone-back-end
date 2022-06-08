package lk.ijse.dep8.tasks.dao.custom.impl;

import lk.ijse.dep8.tasks.dao.custom.TaskListDAO;
import lk.ijse.dep8.tasks.entities.TaskList;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class TaskListDAOImpl implements TaskListDAO {

    private Session session;

    public TaskListDAOImpl(Session session){
        this.session=session;
    }

    @Override
    public boolean existById(Integer pk) {
    return findById(pk).isPresent();
    }

    @Override
    public TaskList save(TaskList entity) {
      session.save(entity);
      return entity;
    }

    @Override
    public void deleteById(Integer pk) {

        session.delete(session.load(TaskList.class,pk));
    }

    @Override
    public Optional<TaskList> findById(Integer pk) {
        TaskList taskList = session.get(TaskList.class, pk);
        return (taskList==null)?Optional.empty():Optional.of(taskList);

    }

    @Override
    public List<TaskList> findAll() {
        return session.createQuery("FROM TaskList tl",TaskList.class).list();
    }

    @Override
    public long count() {
       return session.createQuery("SELECT COUNT(tl) FROM TaskList",Long.class).uniqueResult();
    }

    @Override
    public boolean existTaskListByIdAndUserId(int taskListId, String userId) {
        return false;
    }

    @Override
    public Optional<TaskList> getTaskListByIdAndUserId(int taskListId, String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskList> findByUserId(String userId) {
        return Optional.empty();
    }
}
