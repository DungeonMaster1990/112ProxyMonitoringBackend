package Monitoring.Monitoring.dto.api.viewmodels.response;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlAccidentStatusType;

import java.time.ZonedDateTime;


public class VmAccidentsResponse {
    private String id;
    private String name;
    private int category;
    private String description;
    private String[] affectedSystems;
    private ZonedDateTime detectionDate;
    private String status;
    private BlAccidentStatusType statusType;

    public VmAccidentsResponse(String id, String name, int category, String description, String[] affectedSystems, ZonedDateTime detectionDate, String status, BlAccidentStatusType statusType) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.affectedSystems = affectedSystems;
        this.detectionDate = detectionDate;
        this.status = status;
        this.statusType = statusType;
    }

    public VmAccidentsResponse(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getAffectedSystems() {
        return affectedSystems;
    }

    public void setAffectedSystems(String[] affectedSystems) {
        this.affectedSystems = affectedSystems;
    }

    public ZonedDateTime getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(ZonedDateTime detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BlAccidentStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(BlAccidentStatusType statusType) {
        this.statusType = statusType;
    }
}
