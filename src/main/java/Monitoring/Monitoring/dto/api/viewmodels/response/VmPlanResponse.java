package Monitoring.Monitoring.dto.api.viewmodels.response;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlPlanStatusType;

import java.time.ZonedDateTime;

public class VmPlanResponse {
    private String id;
    private String name;
    private String status;
    private BlPlanStatusType statusType;
    private String description;
    private String[] affectedSystems;
    private ZonedDateTime startDate;

    public VmPlanResponse(String id, String name, String status, BlPlanStatusType statusType, String description, String[] affectedSystems, ZonedDateTime startDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.statusType = statusType;
        this.description = description;
        this.affectedSystems = affectedSystems;
        this.startDate = startDate;
    }

    public VmPlanResponse(){}

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BlPlanStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(BlPlanStatusType statusType) {
        this.statusType = statusType;
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

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }
}
