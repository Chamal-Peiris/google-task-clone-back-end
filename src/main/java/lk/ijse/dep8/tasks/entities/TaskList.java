package lk.ijse.dep8.tasks.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskList implements SuperEntity {
    int id;
    String name;
    String userId;

}
