package Monitoring.Monitoring.db.models;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="accidents")
public class Accident {


    public Accident() {
    }

    public Accident(int id, String foreignId, String name, int priority, int statusId, String shortDescription, String description, String impactDescription, String failurePoint, Calendar detectionDate, Calendar startDate, Calendar finishDate, Calendar predictDate, String affectedSystems, String localizationAndRemediationActions, int specialistUserId) {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "foreignId", unique = false, nullable = false)
    private String foreignId;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "priority", unique = false, nullable = true)
    private int priority;

    @Column(name = "statusId", unique = false, nullable = true)
    private int statusId;

    @Column(name = "shortDescription", unique = false, nullable = true)
    private String shortDescription;

    @Column(name = "description", unique = false, nullable = true)
    private String description;

    @Column(name = "impactDescription", unique = false, nullable = true)
    private String impactDescription;

    @Column(name = "failurePoint", unique = false, nullable = true)
    private String failurePoint;

    @Column(name = "detectionDate", unique = false, nullable = false)
    private Calendar detectionDate;

    @Column(name = "startDate", unique = false, nullable = true)
    private Calendar startDate;

    @Column(name = "finishDate", unique = false, nullable = true)
    private Calendar finishDate;

    @Column(name = "predictDate", unique = false, nullable = true)
    private Calendar predictDate;

    @Column(name = "affectedSystems", unique = false, nullable = true)
    private String affectedSystems;

    @Column(name = "localizationAndRemediationActions", unique = false, nullable = true)
    private String localizationAndRemediationActions;

    @Column(name = "specialistUserId", unique = false, nullable = true)
    private int specialistUserId;

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

    public Calendar getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Calendar detectionDate) {
        this.detectionDate = detectionDate;
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

    public Calendar getPredictDate() {
        return predictDate;
    }

    public void setPredictDate(Calendar predictDate) {
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

    public int getSpecialistUserId() {
        return specialistUserId;
    }

    public void setSpecialistUserId(int specialistUserId) {
        this.specialistUserId = specialistUserId;
    }
}
