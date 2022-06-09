package lk.ijse.dep8.tasks.dao.impl;

import lk.ijse.dep8.tasks.dao.CrudDao;
import lk.ijse.dep8.tasks.entities.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class CrudDAOImpl <T extends SuperEntity,ID extends Serializable> implements CrudDao<T,ID> {

    @Override
    public boolean existById(ID pk) {
        return false;
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public void deleteById(ID pk) {

    }

    @Override
    public Optional<T> findById(ID pk) {
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
