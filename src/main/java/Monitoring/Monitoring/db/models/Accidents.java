package Monitoring.Monitoring.db.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="accidents", schema = "monitoring")
public class Accidents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "foreign_id", unique = false, nullable = false)
    private String foreignId;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "priority", unique = false, nullable = true)
    private int priority;

    @Column(name = "status_id", unique = false, nullable = true)
    private int statusId;

    @Column(name = "short_description", unique = false, nullable = true)
    private String shortDescription;

    @Column(name = "description", unique = false, nullable = true)
    private String description;

    @Column(name = "impact_description", unique = false, nullable = true)
    private String impactDescription;

    @Column(name = "failure_point", unique = false, nullable = true)
    private String failurePoint;

    @Column(name = "detection_date", unique = false, nullable = false)
    private ZonedDateTime detectionDate;

    @Column(name = "start_date", unique = false, nullable = true)
    private ZonedDateTime startDate;

    @Column(name = "finish_date", unique = false, nullable = true)
    private ZonedDateTime finishDate;

    @Column(name = "predict_date", unique = false, nullable = true)
    private ZonedDateTime predictDate;

    @Column(name = "affected_systems", unique = false, nullable = true)
    private String affectedSystems;

    @Column(name = "localization_and_remediation_actions", unique = false, nullable = true)
    private String localizationAndRemediationActions;

    @Column(name = "specialist_user_d", unique = false, nullable = true)
    private Integer specialistUserId;

    public Accidents() {}

    public Accidents(int id, String foreignId, String name, int priority, int statusId, String shortDescription, String description, String impactDescription, String failurePoint, ZonedDateTime detectionDate, ZonedDateTime startDate, ZonedDateTime finishDate, ZonedDateTime predictDate, String affectedSystems, String localizationAndRemediationActions, Integer specialistUserId) {
        this.id = id;
        this.foreignId = foreignId;
        this.name = name;
        this.priority = priority;
        this.statusId = statusId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.impactDescription = impactDescription;
        this.failurePoint = failurePoint;
        this.detectionDate = detectionDate;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.predictDate = predictDate;
        this.affectedSystems = affectedSystems;
        this.localizationAndRemediationActions = localizationAndRemediationActions;
        this.specialistUserId = specialistUserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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

    public ZonedDateTime getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(ZonedDateTime detectionDate) {
        this.detectionDate = detectionDate;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(ZonedDateTime finishDate) { this.finishDate = finishDate; }

    public ZonedDateTime getPredictDate() {
        return predictDate;
    }

    public void setPredictDate(ZonedDateTime predictDate) {
        this.predictDate = predictDate;
    }

    public String getAffectedSystems() {
        return affectedSystems;
    }

    public void setAffectedSystems(String affectedSystems) {
        this.affectedSystems = affectedSystems;
    }

    public String getLocalizationAndRemediationActions() {
        return localizationAndRemediationActions;
    }

    public void setLocalizationAndRemediationActions(String localizationAndRemediationActions) {
        this.localizationAndRemediationActions = localizationAndRemediationActions;
    }

    public Integer getSpecialistUserId() {
        return specialistUserId;
    }

    public void setSpecialistUserId(Integer specialistUserId) {
        this.specialistUserId = specialistUserId;
    }
}
