package lk.ijse.dep8.tasks.dao;

import lk.ijse.dep8.tasks.dao.impl.SuperDAO;
import lk.ijse.dep8.tasks.entities.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T extends SuperEntity,ID extends Serializable> extends SuperDAO {
    boolean existById(ID pk);
    T save(T entity);
    void deleteById(ID pk);
    Optional<T> findById(ID pk);
    List<T> findAll();
    long count();
}
