package lk.ijse.dep8.tasks.dao.impl;

import lk.ijse.dep8.tasks.dao.CrudDao;
import lk.ijse.dep8.tasks.entities.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDao<T, ID> {

    protected Session session;

    private Class<T> entityClassObj;

    public CrudDAOImpl() {
        Class<T> actualTypeArgument = (Class<T>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

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
