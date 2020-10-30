package Monitoring.Monitoring.db.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="vtbUnavailability", schema = "monitoring")
public class VtbUnavailability {
    public VtbUnavailability(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    private int serviceId;

    @Column(name = "created_at", unique = false, nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "created_by_id", unique = false, nullable = false)
    private String createdById;

    @Column(name = "updated_at", unique = false, nullable = false)
    private ZonedDateTime updatedAt;

    @Column(name = "updated_by_id", unique = false, nullable = false)
    private int updatedById;

    public VtbUnavailability(int id, ZonedDateTime beginAt, ZonedDateTime endAt, String duration, String serviceName, String type, int serviceId, ZonedDateTime createdAt, String createdById, ZonedDateTime updatedAt, int updatedById) {
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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
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

    public int getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(int updatedById) {
        this.updatedById = updatedById;
    }
}
