package lk.ijse.dep8.tasks.entities;

import java.io.Serializable;

public class User implements Serializable {
    String id;
    String email;
    String password;
    String fullName;
    String profilePic;

    public User() {
    }

    public User(String id, String email, String password, String fullName, String profilePic) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.profilePic = profilePic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
