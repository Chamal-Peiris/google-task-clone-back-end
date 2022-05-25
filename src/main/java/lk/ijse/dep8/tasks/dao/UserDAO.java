package lk.ijse.dep8.tasks.dao;

import lk.ijse.dep8.tasks.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends SuperDAO {

    public boolean existsUserByEmailOrId(String emailOrId);

    public Optional<User> findUserByIdOrEmail(String userIdOrEmail);



}
