package Monitoring.Monitoring.db.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="unavailabilities", schema = "monitoring")
public class Unavailabilities {
    public Unavailabilities(){}
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    public String getFaultId() {
        return FaultId;
    }

    public void setFaultId(String faultId) {
        FaultId = faultId;
    }

    @Column(name = "fault_id", unique = false, nullable = false)
    private String FaultId;

    @Column(name = "begin_at", unique = false, nullable = false)
    private ZonedDateTime beginAt;

    @Column(name = "end_at", unique = false, nullable = false)
    private ZonedDateTime endAt;

    @Column(name = "duration", unique = false, nullable = false)
    private String duration;

    @Column(name = "service_name", unique = false, nullable = false)
    private String serviceName;

    @Column(name = "type", unique = false, nullable = false)
    private String type;

    @Column(name = "service_id", unique = false, nullable = false)
    private String serviceId;

    @Column(name = "created_at", unique = false, nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "created_by_id", unique = false, nullable = false)
    private String createdById;

    @Column(name = "updated_at", unique = false, nullable = false)
    private ZonedDateTime updatedAt;

    @Column(name = "updated_by_id", unique = false, nullable = false)
    private Integer updatedById;

    public Unavailabilities(int id, ZonedDateTime beginAt, ZonedDateTime endAt, String duration, String serviceName, String type, String serviceId, ZonedDateTime createdAt, String createdById, ZonedDateTime updatedAt, Integer updatedById) {
        this.id = id;
        this.beginAt = beginAt;
        this.endAt = endAt;
        this.duration = duration;
        this.serviceName = serviceName;
        this.type = type;
        this.serviceId = serviceId;
        this.createdAt = createdAt;
        this.createdById = createdById;
        this.updatedAt = updatedAt;
        this.updatedById = updatedById;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ZonedDateTime getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(ZonedDateTime beginAt) {
        this.beginAt = beginAt;
    }

    public ZonedDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(ZonedDateTime endAt) {
        this.endAt = endAt;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }
}
