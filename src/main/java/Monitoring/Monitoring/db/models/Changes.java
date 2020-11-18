package Monitoring.Monitoring.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="changes", schema = "monitoring")
public class Changes implements BaseSmModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;



    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
