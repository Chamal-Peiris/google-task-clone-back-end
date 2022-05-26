package lk.ijse.dep8.tasks.dao.custom;

import lk.ijse.dep8.tasks.dao.CrudDao;
import lk.ijse.dep8.tasks.entities.User;

import java.util.Optional;

public interface UserDAO extends CrudDao<User,String> {

//    boolean existsById(String userId);

    public boolean existsUserByEmailOrId(String emailOrId);

    public Optional<User> findUserByIdOrEmail(String userIdOrEmail);



}
