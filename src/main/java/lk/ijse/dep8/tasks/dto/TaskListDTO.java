package lk.ijse.dep8.tasks.dto;

import jakarta.json.bind.annotation.JsonbTransient;

import java.io.Serializable;

public class TaskListDTO implements Serializable {
    private Integer id;
    private String title;

    @JsonbTransient
    private String user_id;

    public TaskListDTO() {
    }

    public TaskListDTO(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public TaskListDTO(Integer id, String title, String user_id) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
