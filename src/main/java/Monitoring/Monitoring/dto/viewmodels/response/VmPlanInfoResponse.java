package Monitoring.Monitoring.dto.viewmodels.response;

import Monitoring.Monitoring.dto.viewmodels.enums.BlPlanStatus;

import java.util.Calendar;

public class VmPlanInfoResponse {
    private String id;
    private String name;
    private String status;
    private BlPlanStatus statusType;
    private String description;
    private String impactDescription;
    private String degradationRate;
    private String configurationUnit;
    private String[] affectedSystems;
    private Calendar startDate;
    private Calendar finishDate;
    private Calendar startDownDate;
    private Calendar finishDownDate;

    public VmPlanInfoResponse(String id, String name, String status, BlPlanStatus statusType, String description, String impactDescription, String degradationRate, String configurationUnit, String[] affectedSystems, Calendar startDate, Calendar finishDate, Calendar startDownDate, Calendar finishDownDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.statusType = statusType;
        this.description = description;
        this.impactDescription = impactDescription;
        this.degradationRate = degradationRate;
        this.configurationUnit = configurationUnit;
        this.affectedSystems = affectedSystems;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.startDownDate = startDownDate;
        this.finishDownDate = finishDownDate;
    }

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

    public BlPlanStatus getStatusType() {
        return statusType;
    }

    public void setStatusType(BlPlanStatus statusType) {
        this.statusType = statusType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImpactDescription() {
        return impactDescription;
    }

    public void setImpactDescription(String impactDescription) {
        this.impactDescription = impactDescription;
    }

    public String getDegradationRate() {
        return degradationRate;
    }

    public void setDegradationRate(String degradationRate) {
        this.degradationRate = degradationRate;
    }

    public String getConfigurationUnit() {
        return configurationUnit;
    }

    public void setConfigurationUnit(String configurationUnit) {
        this.configurationUnit = configurationUnit;
    }

    public String[] getAffectedSystems() {
        return affectedSystems;
    }

    public void setAffectedSystems(String[] affectedSystems) {
        this.affectedSystems = affectedSystems;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Calendar finishDate) {
        this.finishDate = finishDate;
    }

    public Calendar getStartDownDate() {
        return startDownDate;
    }

    public void setStartDownDate(Calendar startDownDate) {
        this.startDownDate = startDownDate;
    }

    public Calendar getFinishDownDate() {
        return finishDownDate;
    }

    public void setFinishDownDate(Calendar finishDownDate) {
        this.finishDownDate = finishDownDate;
    }
}
