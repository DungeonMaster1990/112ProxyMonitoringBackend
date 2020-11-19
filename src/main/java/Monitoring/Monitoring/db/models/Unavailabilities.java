package Monitoring.Monitoring.db.models;

import Monitoring.Monitoring.db.models.types.Interval;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

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

    public String getFaultId() {
        return FaultId;
    }

    public void setFaultId(String faultId) {
        FaultId = faultId;
    }

    @Column(name = "fault_id", nullable = false)
    private String FaultId;

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
}
