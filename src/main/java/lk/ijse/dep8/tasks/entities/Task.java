package lk.ijse.dep8.tasks.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task implements Serializable {
    int id;
    String title;
    String details;
    int position;
    Status status;
    int taskListId;



    public enum Status{
        completed,needsAction
    }
}
