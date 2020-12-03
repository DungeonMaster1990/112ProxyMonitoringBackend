package ru.vtb.monitoring.vtb112.db.pg.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sm_rawdata_meas", schema = "monitoring")
public class SmRawdataMeasApi {
    @GenericGenerator(
            name = "measRawDataIdGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "monitoring.sm_rawdata_meas_id_seq")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measRawDataIdGenerator")
    @Id
    private Integer id;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @Column(name = "time_stamp", nullable = false)
    private ZonedDateTime timeStamp;

    @Column(name = "measurement_id", nullable = false)
    private Integer measurementId;

    @Column(name = "meas_value", nullable = false)
    private Float measValue;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "err_msg")
    private String errMsg;

    @Column(name = "raw_monitor_id", nullable = false)
    private Integer rawMonitorId;

    @Column(name = "raw_target_id", nullable = false)
    private Integer rawTargetId;

    @Column(name = "raw_connection_id", nullable = false)
    private Integer rawConnectionId;

    @Column(name = "raw_category_id", nullable = false)
    private Integer rawCategoryId;

    @Column(name = "raw_threshold_quality")
    private Integer rawThresholdQuality;

    @Column(name = "dbdate")
    private ZonedDateTime dbdate;
}
