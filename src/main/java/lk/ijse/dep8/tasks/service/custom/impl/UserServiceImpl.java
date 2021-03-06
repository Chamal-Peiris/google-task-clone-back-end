package lk.ijse.dep8.tasks.service.custom.impl;

import lk.ijse.dep8.tasks.dao.DAOFactory;
import lk.ijse.dep8.tasks.dao.custom.UserDAO;
import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.entities.User;
import lk.ijse.dep8.tasks.service.custom.UserService;
import lk.ijse.dep8.tasks.service.exception.FailedExecutionException;
import lk.ijse.dep8.tasks.service.util.EntityDtoMapper;
import lk.ijse.dep8.tasks.service.util.HibernateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    public boolean existsUser(String userIdOrEmail) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            UserDAO userDAO = DAOFactory.getInstance().getDAO(session, DAOFactory.DAOTypes.USER);
            return userDAO.existsUserByEmailOrId(userIdOrEmail);
        }
    }

    public UserDTO registerUser(Part picture,
                                String appLocation,
                                UserDTO user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            user.setId(UUID.randomUUID().toString());

            if (picture != null) {
                user.setPicture(user.getPicture() + user.getId());
            }
            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

            UserDAO userDAO = DAOFactory.getInstance().getDAO(session, DAOFactory.DAOTypes.USER);
            // DTO -> Entity
            User userEntity = EntityDtoMapper.getUser(user);
            User savedUser = userDAO.save(userEntity);
            // Entity -> DTO
            user = EntityDtoMapper.getUserDTO(savedUser);

            if (picture != null) {
                Path path = Paths.get(appLocation, "uploads");
                if (Files.notExists(path)) {
                    Files.createDirectory(path);
                }

                String picturePath = path.resolve(user.getId()).toAbsolutePath().toString();
                picture.write(picturePath);
            }

            session.getTransaction().commit();
            return user;
        } catch (Throwable t) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new FailedExecutionException("Failed to save the user", t);
        } finally {
            session.close();
        }
    }

    public UserDTO getUser(String userIdOrEmail) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            UserDAO userDAO = DAOFactory.getInstance().getDAO(session, DAOFactory.DAOTypes.USER);
            Optional<User> userWrapper = userDAO.findUserByIdOrEmail(userIdOrEmail);
            return EntityDtoMapper.getUserDTO(userWrapper.orElse(null));
        }
    }

    public void deleteUser(String userId, String appLocation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            UserDAO userDAO = DAOFactory.getInstance().getDAO(session, DAOFactory.DAOTypes.USER);
            userDAO.deleteById(userId);
            session.getTransaction().commit();

            new Thread(() -> {
                Path imagePath = Paths.get(appLocation, "uploads",
                        userId);
                try {
                    Files.deleteIfExists(imagePath);
                } catch (IOException e) {
                    logger.warning("Failed to delete the image: " + imagePath.toAbsolutePath());
                }
            }).start();
        } catch (Throwable t) {
            if (session != null && session.getTransaction() != null) session.getTransaction().rollback();
            throw new FailedExecutionException("Failed to delete the user", t);
        } finally {
            session.close();
        }

    }

    public void updateUser(UserDTO user, Part picture,
                           String appLocation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

            UserDAO userDAO = DAOFactory.getInstance().getDAO(session, DAOFactory.DAOTypes.USER);

            // Fetch the current user
            User userEntity = userDAO.findById(user.getId()).get();

            userEntity.setPassword(user.getPassword());
            userEntity.setFullName(user.getName());
            userEntity.setProfilePic(user.getPicture());

            userDAO.save(userEntity);

            Path path = Paths.get(appLocation, "uploads");
            Path picturePath = path.resolve(user.getId());

            if (picture != null) {
                if (Files.notExists(path)) {
                    Files.createDirectory(path);
                }

                Files.deleteIfExists(picturePath);
                picture.write(picturePath.toAbsolutePath().toString());
            } else {
                Files.deleteIfExists(picturePath);
            }

            session.getTransaction().commit();
        } catch (Throwable e) {
            if (session != null && session.getTransaction() != null) session.getTransaction().rollback();
            throw new FailedExecutionException("Failed to update the user", e);
        } finally {
            session.close();
        }
    }
}
