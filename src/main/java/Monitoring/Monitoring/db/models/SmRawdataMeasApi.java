package Monitoring.Monitoring.db.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="SM_RAWDATA_MEAS", schema = "bsm_replica")
public class SmRawdataMeasApi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "session_id", unique = false, nullable = false)
    private Integer sessionId;

    @Column(name = "time_stamp", unique = false, nullable = false)
    private ZonedDateTime timeStamp;

    @Column(name = "measurement_id", unique = false, nullable = false)
    private Integer measurementId;

    @Column(name = "meas_value", unique = false, nullable = false)
    private Float measValue;

    @Column(name = "status_id", unique = false, nullable = true)
    private Integer statusId;

    @Column(name = "err_msg", unique = false, nullable = true)
    private String errMsg;

    @Column(name = "raw_monitor_id", unique = false, nullable = false)
    private Integer rawMonitorId;

    @Column(name = "raw_target_id", unique = false, nullable = false)
    private Integer rawTargetId;

    @Column(name = "raw_connection_id", unique = false, nullable = false)
    private Integer rawConnectionId;

    @Column(name = "raw_category_id", unique = false, nullable = false)
    private Integer rawCategoryId;

    @Column(name = "raw_threshold_quality", unique = false, nullable = true)
    private Integer rawThresholdQuality;

    @Column(name = "dbdate", unique = false, nullable = true)
    private ZonedDateTime dbdate;

    public SmRawdataMeasApi(Integer id, Integer sessionId, ZonedDateTime timeStamp, Integer measurementId, Float measValue, Integer statusId, String errMsg, Integer rawMonitorId, Integer rawTargetId, Integer rawConnectionId, Integer rawCategoryId, Integer rawThresholdQuality, ZonedDateTime dbdate) {
        this.id = id;
        this.sessionId = sessionId;
        this.timeStamp = timeStamp;
        this.measurementId = measurementId;
        this.measValue = measValue;
        this.statusId = statusId;
        this.errMsg = errMsg;
        this.rawMonitorId = rawMonitorId;
        this.rawTargetId = rawTargetId;
        this.rawConnectionId = rawConnectionId;
        this.rawCategoryId = rawCategoryId;
        this.rawThresholdQuality = rawThresholdQuality;
        this.dbdate = dbdate;
    }

    public SmRawdataMeasApi(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
    }

    public Float getMeasValue() {
        return measValue;
    }

    public void setMeasValue(Float measValue) {
        this.measValue = measValue;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getRawMonitorId() {
        return rawMonitorId;
    }

    public void setRawMonitorId(Integer rawMonitorId) {
        this.rawMonitorId = rawMonitorId;
    }

    public Integer getRawTargetId() {
        return rawTargetId;
    }

    public void setRawTargetId(Integer rawTargetId) {
        this.rawTargetId = rawTargetId;
    }

    public Integer getRawConnectionId() {
        return rawConnectionId;
    }

    public void setRawConnectionId(Integer rawConnectionId) {
        this.rawConnectionId = rawConnectionId;
    }

    public Integer getRawCategoryId() {
        return rawCategoryId;
    }

    public void setRawCategoryId(Integer rawCategoryId) {
        this.rawCategoryId = rawCategoryId;
    }

    public Integer getRawThresholdQuality() {
        return rawThresholdQuality;
    }

    public void setRawThresholdQuality(Integer rawThresholdQuality) {
        this.rawThresholdQuality = rawThresholdQuality;
    }

    public ZonedDateTime getDbdate() {
        return dbdate;
    }

    public void setDbdate(ZonedDateTime dbdate) {
        this.dbdate = dbdate;
    }
}
