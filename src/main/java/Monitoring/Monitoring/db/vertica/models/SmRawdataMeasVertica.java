package Monitoring.Monitoring.db.vertica.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="SM_RAWDATA_MEAS", schema = "bsm_replica")
public class SmRawdataMeas {
    @Column(name = "session_id", unique = false, nullable = false)
    private Integer SessionId;

    @Column(name = "time_stamp", unique = false, nullable = false)
    private ZonedDateTime TimeStamp;

    @Column(name = "measurement_id", unique = false, nullable = false)
    private Integer MeasurementId;

    @Column(name = "meas_value", unique = false, nullable = false)
    private Float MeasValue;

    @Column(name = "status_id", unique = false, nullable = true)
    private Integer StatusId;

    @Column(name = "err_msg", unique = false, nullable = true)
    private String ErrMsg;

    @Column(name = "raw_monitor_id", unique = false, nullable = false)
    private Integer RawMonitorId;

    @Column(name = "raw_target_id", unique = false, nullable = false)
    private Integer RawTargetId;

    @Column(name = "raw_connection_id", unique = false, nullable = false)
    private Integer RawConnectionId;

    @Column(name = "raw_category_id", unique = false, nullable = false)
    private Integer RawCategoryId;

    @Column(name = "raw_threshold_quality", unique = false, nullable = true)
    private Integer RawThresholdQuality;

    @Column(name = "dbdate", unique = false, nullable = true)
    private ZonedDateTime Dbdate;

    public SmRawdataMeas(Integer sessionId, ZonedDateTime timeStamp, Integer measurementId, Float measValue, Integer statusId, String errMsg, Integer rawMonitorId, Integer rawTargetId, Integer rawConnectionId, Integer rawCategoryId, Integer rawThresholdQuality, ZonedDateTime dbdate) {
        SessionId = sessionId;
        TimeStamp = timeStamp;
        MeasurementId = measurementId;
        MeasValue = measValue;
        StatusId = statusId;
        ErrMsg = errMsg;
        RawMonitorId = rawMonitorId;
        RawTargetId = rawTargetId;
        RawConnectionId = rawConnectionId;
        RawCategoryId = rawCategoryId;
        RawThresholdQuality = rawThresholdQuality;
        Dbdate = dbdate;
    }

    public Integer getSessionId() {
        return SessionId;
    }

    public void setSessionId(Integer sessionId) {
        SessionId = sessionId;
    }

    public ZonedDateTime getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        TimeStamp = timeStamp;
    }

    public Integer getMeasurementId() {
        return MeasurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        MeasurementId = measurementId;
    }

    public Float getMeasValue() {
        return MeasValue;
    }

    public void setMeasValue(Float measValue) {
        MeasValue = measValue;
    }

    public Integer getStatusId() {
        return StatusId;
    }

    public void setStatusId(Integer statusId) {
        StatusId = statusId;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public Integer getRawMonitorId() {
        return RawMonitorId;
    }

    public void setRawMonitorId(Integer rawMonitorId) {
        RawMonitorId = rawMonitorId;
    }

    public Integer getRawTargetId() {
        return RawTargetId;
    }

    public void setRawTargetId(Integer rawTargetId) {
        RawTargetId = rawTargetId;
    }

    public Integer getRawConnectionId() {
        return RawConnectionId;
    }

    public void setRawConnectionId(Integer rawConnectionId) {
        RawConnectionId = rawConnectionId;
    }

    public Integer getRawCategoryId() {
        return RawCategoryId;
    }

    public void setRawCategoryId(Integer rawCategoryId) {
        RawCategoryId = rawCategoryId;
    }

    public Integer getRawThresholdQuality() {
        return RawThresholdQuality;
    }

    public void setRawThresholdQuality(Integer rawThresholdQuality) {
        RawThresholdQuality = rawThresholdQuality;
    }

    public ZonedDateTime getDbdate() {
        return Dbdate;
    }

    public void setDbdate(ZonedDateTime dbdate) {
        Dbdate = dbdate;
    }
}
