package ru.vtb.monitoring.vtb112.vertica.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "SM_RAWDATA_MEAS", schema = "bsm_replica")
public class SmRawdataMeasVertica {

    @Id
    @Column(name = "session_id")
    private Integer sessionId;

    @Column(name = "time_stamp")
    private ZonedDateTime timeStamp;

    @Column(name = "measurement_id")
    private Integer measurementId;

    @Column(name = "meas_value")
    private Float measValue;

    @Nullable
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "err_msg")
    private String errMsg;

    @Column(name = "raw_monitor_id")
    private Integer rawMonitorId;

    @Column(name = "raw_target_id")
    private Integer rawTargetId;

    @Column(name = "raw_connection_id")
    private Integer rawConnectionId;

    @Column(name = "raw_category_id")
    private Integer rawCategoryId;

    @Nullable
    @Column(name = "raw_threshold_quality")
    private Integer rawThresholdQuality;

    @Nullable
    @Column(name = "dbdate")
    private ZonedDateTime dbdate;

}

