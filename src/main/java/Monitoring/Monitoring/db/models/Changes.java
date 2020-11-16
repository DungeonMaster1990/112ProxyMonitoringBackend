package Monitoring.Monitoring.db.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="changes", schema = "monitoring")
public class Changes implements BaseSmModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Override
    public ZonedDateTime getUpdatedAt() {
        return null;
    }
    public Changes(){}
}
