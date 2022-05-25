package lk.ijse.dep8.tasks.dao;

import lk.ijse.dep8.tasks.entities.SuperEntity;
import lk.ijse.dep8.tasks.entities.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface SuperDAO<T extends SuperEntity,ID extends Serializable> {
    boolean existById(ID pk);
    T save(T entity);
    void deleteById(ID pk);
    Optional<T> findById(ID pk);
    List<T> findAll();
    long count();
}
