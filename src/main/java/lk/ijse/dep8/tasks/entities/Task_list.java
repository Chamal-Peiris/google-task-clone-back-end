package lk.ijse.dep8.tasks.entities;

import java.io.Serializable;

public class Task_list implements Serializable {
    int id;
    String name;
    String userId;

    public Task_list() {
    }

    public Task_list(int id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
