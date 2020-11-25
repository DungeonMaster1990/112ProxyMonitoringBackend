package ru.vtb.monitoring.vtb112.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="systems", schema = "monitoring")
@NoArgsConstructor
public class AffectedSystem {

    @Id
    @GenericGenerator(
            name = "affectedSystemIdGenerator",
            strategy = "sequence-identity",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence",
                            value = "monitoring.systems_id_seq")
            }
    )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "affectedSystemIdGenerator")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "accidentid", nullable = false, insertable = false, updatable = false)
    private Integer incidentId;

    @ManyToOne
    @JoinColumn(name="accidentid", referencedColumnName="id")
    private Incident incident;

}
