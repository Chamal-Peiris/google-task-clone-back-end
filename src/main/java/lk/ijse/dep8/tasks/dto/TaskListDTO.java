package lk.ijse.dep8.tasks.dto;

import java.io.Serializable;

public class TaskListDTO implements Serializable {
    private String id;
    private String title;

    public TaskListDTO() {
    }

    public TaskListDTO(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}