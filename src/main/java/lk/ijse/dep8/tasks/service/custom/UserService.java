package lk.ijse.dep8.tasks.service.custom;

import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.service.SuperSevice;

import javax.servlet.http.Part;
import java.sql.SQLException;

public interface UserService extends SuperSevice {

    public  boolean existsUser( String userIdOrEmail);
    public UserDTO registerUser( Part picture,
                                String appLocation,
                                UserDTO user) throws SQLException;
    public  UserDTO getUser(String userIdOrEmail);
    public  void deleteUser( String userId, String appLocation);
    public  void updateUser(UserDTO user, Part picture,
                            String appLocation);

}
