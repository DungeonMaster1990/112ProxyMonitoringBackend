package ru.vtb.monitoring.vtb112.db.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sm_def_measurements", schema = "monitoring")
public class SmDefMeasurementApi {
    @Id
    @GenericGenerator(
            name = "measurementsSeq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "monitoring.sm_def_measurements_id_seq")
            }
    )
    @GeneratedValue(generator = "measurementsSeq")
    private Integer id;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @Column(name = "measurement_id", nullable = false)
    private Integer measurementId;

    @Column(name = "sched_id", nullable = false)
    private Integer schedId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "monitor_id")
    private Integer monitorId;

    @Column(name = "target_id", nullable = false)
    private Integer targetId;

    @Column(name = "msname", nullable = false)
    private String msname;

    @Column(name = "Msid")
    private String msid;

    @Column(name = "user_remark")
    private String userRemark;

    @Column(name = "connection_data")
    private String connectionData;

    @Column(name = "dm_connection_id")
    private Integer dmConnectionId;

    @Column(name = "is_active", nullable = false)
    private Integer active;

    @Column(name = "ci_id")
    private String ciId;

    @Column(name = "eti_id")
    private String etiId;

    @Column(name = "integration_name")
    private String integrationName;

    @Column(name = "profile_id")
    private String profileId;

    @Column(name = "modified_date", nullable = false)
    private ZonedDateTime modifiedDate;

    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creationDate;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
