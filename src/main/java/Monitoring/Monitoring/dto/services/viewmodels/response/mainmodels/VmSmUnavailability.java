package Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmUnavailability {
    private String faultId;
    private ZonedDateTime beginAt;
    private ZonedDateTime endAt;
    private String duration;
    private String serviceName;
    private String type;
    private String serviceId;
    private ZonedDateTime createdAt;
    private String createdById;
    private ZonedDateTime updatedAt;
    private int updatedById;

    public VmSmUnavailability(String faultId, ZonedDateTime beginAt, ZonedDateTime endAt, String duration, String serviceName, String type, String serviceId, ZonedDateTime createdAt, String createdById, ZonedDateTime updatedAt, int updatedById) {
        this.faultId = faultId;
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

    public String getFaultId() {
        return faultId;
    }

    public void setFaultId(String faultId) {
        this.faultId = faultId;
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

    public int getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(int updatedById) {
        this.updatedById = updatedById;
    }
}
