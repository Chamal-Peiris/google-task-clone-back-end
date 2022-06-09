package lk.ijse.dep8.tasks.dao.impl;

import lk.ijse.dep8.tasks.dao.custom.UserDAO;
import lk.ijse.dep8.tasks.entities.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends CrudDAOImpl<User,String> implements UserDAO {


public UserDAOImpl(Session session){
    this.session=session;
}


    @Override
    public boolean existsUserByEmailOrId(String emailOrId) {
     return   findUserByIdOrEmail(emailOrId).isPresent();
    }

    @Override
    public Optional<User> findUserByIdOrEmail(String userIdOrEmail) {
        return   session.createQuery("FROM User u WHERE u.id=?1 OR u.email=?2",User.class).setParameter(1,userIdOrEmail).setParameter(2,userIdOrEmail)
                .uniqueResultOptional();
    }
}
