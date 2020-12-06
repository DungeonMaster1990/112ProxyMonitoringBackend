package ru.vtb.monitoring.vtb112.db.pg.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import ru.vtb.monitoring.vtb112.db.pg.models.types.Interval;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "unavailability", schema = "monitoring")
@Setter
@Getter
@NoArgsConstructor
@TypeDef(name = "interval", typeClass = Interval.class)
public class Unavailabilities implements BaseSmModel {
    @Id
    @GenericGenerator(
            name = "unavailabilityIdGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "monitoring.unavailability_id_seq")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unavailabilityIdGenerator")
    private int id;

    @Column(name = "fault_id", nullable = false)
    private String faultId;

    @Column(name = "begin_at", nullable = false)
    private ZonedDateTime beginAt;

    @Column(name = "end_at", nullable = false)
    private ZonedDateTime endAt;

    @Column(name = "duration", columnDefinition = "interval", nullable = false)
    @Type(type = "interval")
    private Duration duration;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "created_by_id", nullable = false)
    private String createdById;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @Column(name = "updated_by_id", nullable = false)
    private Integer updatedById;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "fault_id", referencedColumnName = "incident_id", insertable = false, updatable = false)
    private Incident incident;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unavailabilities that = (Unavailabilities) o;
        return Objects.equals(faultId, that.faultId) &&
                Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faultId, serviceId);
    }
}
