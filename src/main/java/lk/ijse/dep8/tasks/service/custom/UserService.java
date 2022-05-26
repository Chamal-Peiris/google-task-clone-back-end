package lk.ijse.dep8.tasks.service.custom;

import lk.ijse.dep8.tasks.dao.DaoFactory;
import lk.ijse.dep8.tasks.dao.custom.UserDAO;
import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.entities.User;
import lk.ijse.dep8.tasks.service.SuperSevice;
import lombok.experimental.SuperBuilder;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public interface UserService extends SuperSevice {

}
