package ru.vtb.monitoring.vtb112.db.vertica.models;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SM_DEF_MEASUREMENT", schema = "bsm_replica")
public class SmDefMeasurementVertica {

    @Id
    @Column(name = "session_id")
    private Integer sessionId;

    @Column(name = "measurement_id")
    private Integer measurementId;

    @Column(name = "sched_id")
    private Integer schedId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Nullable
    @Column(name = "monitor_id")
    private Integer monitorId;

    @Column(name = "target_id")
    private Integer targetId;

    @Column(name = "msname")
    private String msname;

    @Nullable
    @Column(name = "msid")
    private String msid;

    @Nullable
    @Column(name = "user_remark")
    private String userRemark;

    @Nullable
    @Column(name = "connection_data")
    private String connectionData;

    @Nullable
    @Column(name = "dm_connection_id")
    private Integer dmConnectionId;

    @Column(name = "active")
    private Integer active;

    @Nullable
    @Column(name = "ci_id")
    private String ciId;

    @Nullable
    @Column(name = "eti_id")
    private String etiId;

    @Nullable
    @Column(name = "integration_name")
    private String integrationName;

    @Nullable
    @Column(name = "profile_id")
    private String profileId;

    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}

