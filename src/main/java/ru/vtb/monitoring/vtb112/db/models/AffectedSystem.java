package ru.vtb.monitoring.vtb112.db.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "systems", schema = "monitoring")
public class AffectedSystem {

    @Id
    @GenericGenerator(
            name = "affectedSystemIdGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "monitoring.systems_id_seq")
            }
    )
    @GeneratedValue(generator = "affectedSystemIdGenerator")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "accidentid", nullable = false, insertable = false, updatable = false)
    private Integer incidentId;

    @ManyToOne
    @JoinColumn(name = "accidentid", referencedColumnName = "id")
    private Incident incident;

}
