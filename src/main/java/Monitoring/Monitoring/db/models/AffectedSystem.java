package Monitoring.Monitoring.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="systems", schema = "monitoring")
@NoArgsConstructor
public class AffectedSystem {

    @Id
    @GeneratedValue(generator = "monitoring.systems_id_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "accidentid", nullable = false)
    private Integer incidentId;

}
