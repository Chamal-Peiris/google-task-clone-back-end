package lk.ijse.dep8.tasks.dao;

import java.util.List;
import java.util.Optional;

public interface SuperDAO {
    boolean existById(Object pk);
    Object save(Object entity);
    void deleteById(Object pk);
    Optional<Object> findById(Object pk);
    List<Object> findAll();
    long count();
}
