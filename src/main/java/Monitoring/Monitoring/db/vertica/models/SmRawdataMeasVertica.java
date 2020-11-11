package Monitoring.Monitoring.db.vertica.models;

import java.time.ZonedDateTime;

public class SmRawdataMeasVertica {
    private Integer sessionId;

    private ZonedDateTime timeStamp;

    private Integer measurementId;

    private Float measValue;

    private Integer statusId;

    private String errMsg;

    private Integer rawMonitorId;

    private Integer rawTargetId;

    private Integer rawConnectionId;

    private Integer rawCategoryId;

    private Integer rawThresholdQuality;

    private ZonedDateTime dbdate;

    public SmRawdataMeasVertica(Integer sessionId, ZonedDateTime timeStamp, Integer measurementId, Float measValue, Integer statusId, String errMsg, Integer rawMonitorId, Integer rawTargetId, Integer rawConnectionId, Integer rawCategoryId, Integer rawThresholdQuality, ZonedDateTime dbdate) {
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
        return this.rawThresholdQuality;
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
