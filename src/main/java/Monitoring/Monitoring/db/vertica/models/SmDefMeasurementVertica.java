package Monitoring.Monitoring.db.vertica.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name="SM_DEF_MEASUREMENT", schema = "bsm_replica")
public class SmDefMeasurement {
    @Column(name = "session_id", unique = false, nullable = false)
    private Integer SessionId;

    @Column(name = "measurement_id", unique = false, nullable = false)
    private Integer MeasurementId;

    @Column(name = "sched_id", unique = false, nullable = false)
    private Integer SchedId;

    @Column(name = "category_id", unique = false, nullable = false)
    private Integer CategoryId;

    @Column(name = "monitor_id", unique = false, nullable = true)
    private Integer MonitorId;

    @Column(name = "target_id", unique = false, nullable = false)
    private Integer TargetId;

    @Column(name = "msname", unique = false, nullable = false)
    private String Msname;

    @Column(name = "Msid", unique = false, nullable = true)
    private String Msid;

    @Column(name = "user_remark", unique = false, nullable = true)
    private String UserRemark;

    @Column(name = "connection_data", unique = false, nullable = true)
    private String ConnectionData;

    @Column(name = "dm_connection_id", unique = false, nullable = true)
    private Integer DmConnectionId;

    @Column(name = "active", unique = false, nullable = false)
    private Integer Active;

    @Column(name = "ci_id", unique = false, nullable = true)
    private Character CiId;

    @Column(name = "eti_id", unique = false, nullable = true)
    private Character EtiId;

    @Column(name = "integration_name", unique = false, nullable = true)
    private String IntegrationName;

    @Column(name = "profile_id", unique = false, nullable = true)
    private String ProfileId;

    @Column(name = "modified_date", unique = false, nullable = false)
    private ZonedDateTime ModifiedDate;

    @Column(name = "creation_date", unique = false, nullable = false)
    private ZonedDateTime CreationDate;

    @Column(name = "is_deleted", unique = false, nullable = false)
    private boolean IsDeleted;

    public SmDefMeasurement(Integer sessionId, Integer measurementId, Integer schedId, Integer categoryId, Integer monitorId, Integer targetId, String msname, String msid, String userRemark, String connectionData, Integer dmConnectionId, Integer active, Character ciId, Character etiId, String integrationName, String profileId, ZonedDateTime modifiedDate, ZonedDateTime creationDate, boolean isDeleted) {
        SessionId = sessionId;
        MeasurementId = measurementId;
        SchedId = schedId;
        CategoryId = categoryId;
        MonitorId = monitorId;
        TargetId = targetId;
        Msname = msname;
        Msid = msid;
        UserRemark = userRemark;
        ConnectionData = connectionData;
        DmConnectionId = dmConnectionId;
        Active = active;
        CiId = ciId;
        EtiId = etiId;
        IntegrationName = integrationName;
        ProfileId = profileId;
        ModifiedDate = modifiedDate;
        CreationDate = creationDate;
        IsDeleted = isDeleted;
    }

    public SmDefMeasurement(){}

    public Integer getSessionId() {
        return SessionId;
    }

    public void setSessionId(Integer sessionId) {
        SessionId = sessionId;
    }

    public Integer getMeasurementId() {
        return MeasurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        MeasurementId = measurementId;
    }

    public Integer getSchedId() {
        return SchedId;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public Integer getMonitorId() {
        return MonitorId;
    }

    public void setMonitorId(Integer monitorId) {
        MonitorId = monitorId;
    }

    public Integer getTargetId() {
        return TargetId;
    }

    public void setTargetId(Integer targetId) {
        TargetId = targetId;
    }

    public String getMsname() {
        return Msname;
    }

    public void setMsname(String msname) {
        Msname = msname;
    }

    public String getMsid() {
        return Msid;
    }

    public void setMsid(String msid) {
        Msid = msid;
    }

    public String getUserRemark() {
        return UserRemark;
    }

    public void setUserRemark(String userRemark) {
        UserRemark = userRemark;
    }

    public String getConnectionData() {
        return ConnectionData;
    }

    public void setConnectionData(String connectionData) {
        ConnectionData = connectionData;
    }

    public Integer getDmConnectionId() {
        return DmConnectionId;
    }

    public void setDmConnectionId(Integer dmConnectionId) {
        DmConnectionId = dmConnectionId;
    }

    public Integer getActive() {
        return Active;
    }

    public void setActive(Integer active) {
        Active = active;
    }

    public Character getCiId() {
        return CiId;
    }

    public void setCiId(Character ciId) {
        CiId = ciId;
    }

    public Character getEtiId() {
        return EtiId;
    }

    public void setEtiId(Character etiId) {
        EtiId = etiId;
    }

    public String getIntegrationName() {
        return IntegrationName;
    }

    public void setIntegrationName(String integrationName) {
        IntegrationName = integrationName;
    }

    public String getProfileId() {
        return ProfileId;
    }

    public void setProfileId(String profileId) {
        ProfileId = profileId;
    }

    public ZonedDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public ZonedDateTime getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        CreationDate = creationDate;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }
}
