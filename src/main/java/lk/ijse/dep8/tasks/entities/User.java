package lk.ijse.dep8.tasks.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements SuperEntity {
    @Id
    String id;
    String email;
    String password;
    String fullName;
    String profilePic;


}
