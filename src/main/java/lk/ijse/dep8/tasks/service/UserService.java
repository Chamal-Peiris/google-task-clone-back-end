package lk.ijse.dep8.tasks.service;

import lk.ijse.dep8.tasks.dao.UserDAO;
import lk.ijse.dep8.tasks.dto.UserDTO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class UserService {
    public static boolean existUser(Connection connection, String email) throws SQLException {
        return UserDAO.existUser(connection, email);


    }

    public static UserDTO registerUser(Connection connection, Part picture, String pictureUrl,String appLocation, UserDTO user) throws SQLException {
        /* lets start transactions*/
       try{
           user.setId(UUID.randomUUID().toString());
           connection.setAutoCommit(false);
           if (picture != null) {

               pictureUrl += "/uploads/" + user.getId();
           }
           user.setPicture(pictureUrl);

           user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
           UserDTO savedUser = UserDAO.saveUser(connection, user);
           if (picture != null) {

               Path path = Paths.get(appLocation, "uploads");
               if (Files.notExists(path)) {
                   Files.createDirectory(path);
               }

               String picturePath = path.resolve(user.getId()).toAbsolutePath().toString();
               picture.write(picturePath);

           }

           connection.commit();
           return  savedUser;
       } catch (Throwable t) {
          connection.rollback();
          connection.setAutoCommit(true);
          throw  new RuntimeException(t);
       }
    }

    public static void updateUser(Connection connection, UserDTO user, Part picture,
                                  String appLocation) throws SQLException {
        try {
            connection.setAutoCommit(false);

            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            UserDAO.updateUser(connection, user);

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

            connection.commit();
        } catch (Throwable e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally{
            connection.setAutoCommit(true);
        }
    }
    public static void deleteUser(Connection connection,String userId,String application) throws SQLException {

        UserDAO.deleteUser(connection,userId);
        new Thread(() -> {
            Path imagePath = Paths.get(application, "uploads", userId);

            try{
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    public static UserDTO getUser(Connection connection,String userIdOrEmail) throws SQLException {
        return UserDAO.getUser(connection,userIdOrEmail);
    }
}
