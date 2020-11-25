package ru.vtb.monitoring.vtb112.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.vtb.monitoring.vtb112.db.models.types.Interval;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name="unavailability", schema = "monitoring")
@Setter
@Getter
@NoArgsConstructor
@TypeDef(name="interval", typeClass = Interval.class)
public class Unavailabilities implements BaseSmModel {
    @Id
    @GenericGenerator(
            name = "unavailabilityIdGenerator",
            strategy = "sequence-identity",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence",
                            value = "monitoring.unavailability_id_seq")
            }
    )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "unavailabilityIdGenerator")
    private int id;

    @Column(name = "fault_id", nullable = false)
    private String faultId;

    @Column(name = "begin_at", nullable = false)
    private ZonedDateTime beginAt;

    @Column(name = "end_at", nullable = false)
    private ZonedDateTime endAt;

    @Column(name = "duration", columnDefinition = "interval", nullable = false)
    @Type(type = "interval")
    private Integer duration;

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
