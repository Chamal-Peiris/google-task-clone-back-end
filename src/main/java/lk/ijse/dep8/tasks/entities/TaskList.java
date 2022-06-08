package lk.ijse.dep8.tasks.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "task_list")
public class TaskList implements SuperEntity {
    @Id
    int id;
    String name;
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    @ManyToOne
    private User user;
}
