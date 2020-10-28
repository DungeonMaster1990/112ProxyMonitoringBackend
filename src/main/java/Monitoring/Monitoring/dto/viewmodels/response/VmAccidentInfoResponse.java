package Monitoring.Monitoring.dto.viewmodels.response;

import Monitoring.Monitoring.dto.viewmodels.enums.BlAccidentStatusType;

import java.time.ZonedDateTime;

public class VmAccidentInfoResponse {
    private String id;
    private String name;
    private int category;
    private String status;
    private BlAccidentStatusType statusType;
    private String description;
    private String impactDescription;
    private String failurePoint;
    private String configurationUnit;
    private String[] affectedSystems;
    private ZonedDateTime startDate;
    private ZonedDateTime detectionDate;
    private ZonedDateTime predictDate;
    private String conferenceLink;
    private String telegramLink;

    public VmAccidentInfoResponse(String id, String name, int category, String status, BlAccidentStatusType statusType, String description, String impactDescription, String failurePoint, String configurationUnit, String[] affectedSystems, ZonedDateTime startDate, ZonedDateTime detectionDate, ZonedDateTime predictDate, String conferenceLink, String telegramLink) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
        this.statusType = statusType;
        this.description = description;
        this.impactDescription = impactDescription;
        this.failurePoint = failurePoint;
        this.configurationUnit = configurationUnit;
        this.affectedSystems = affectedSystems;
        this.startDate = startDate;
        this.detectionDate = detectionDate;
        this.predictDate = predictDate;
        this.conferenceLink = conferenceLink;
        this.telegramLink = telegramLink;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public String getFailurePoint() {
        return failurePoint;
    }

    public void setFailurePoint(String failurePoint) {
        this.failurePoint = failurePoint;
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

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(ZonedDateTime detectionDate) {
        this.detectionDate = detectionDate;
    }

    public ZonedDateTime getPredictDate() {
        return predictDate;
    }

    public void setPredictDate(ZonedDateTime predictDate) {
        this.predictDate = predictDate;
    }

    public String getConferenceLink() {
        return conferenceLink;
    }

    public void setConferenceLink(String conferenceLink) {
        this.conferenceLink = conferenceLink;
    }

    public String getTelegramLink() {
        return telegramLink;
    }

    public void setTelegramLink(String telegramLink) {
        this.telegramLink = telegramLink;
    }
}
