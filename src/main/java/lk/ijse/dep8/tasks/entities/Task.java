package lk.ijse.dep8.tasks.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String details;
    int position;
    @Enumerated(EnumType.STRING)
    Status status;
    @JoinColumn(name = "task_list_id",referencedColumnName = "id",nullable = false)
    @ManyToOne
    TaskList taskListId;



    public enum Status{
        completed,needsAction
    }
}
