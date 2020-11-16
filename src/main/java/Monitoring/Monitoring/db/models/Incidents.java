package Monitoring.Monitoring.db.models;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="incidents", schema = "monitoring")
public class Incidents implements BaseSmModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "incident_id", unique = false, nullable = false)
    private String incidentId;

    @Column(name = "created_at", unique = false, nullable = true)
    private ZonedDateTime createdAt;

    @Column(name = "expired_at", unique = false, nullable = true)
    private ZonedDateTime expiredAt;

    @Column(name = "author_id", unique = false, nullable = true)
    private Integer authorId;

    @Column(name = "contact_id", unique = false, nullable = true)
    private Integer contactId;

    @Column(name = "service_id", unique = false, nullable = true)
    private Integer serviceId;

    @Column(name = "category", unique = false, nullable = true)
    private String category;

    @Column(name = "failure_point", unique = false, nullable = true)
    private String failurePoint;

    @Column(name = "configuration_item_id", unique = false, nullable = true)
    private Integer configurationItemId;

    @Column(name = "title", unique = false, nullable = true)
    private String title;

    @Column(name = "description", unique = false, nullable = true)
    private String description;

    @Column(name = "impact", unique = false, nullable = true)
    private String impact;

    @Column(name = "severity", unique = false, nullable = true)
    private String severity;

    @Column(name = "group_id", unique = false, nullable = true)
    private Integer groupId;

    @Column(name = "assignee_id", unique = false, nullable = true)
    private String assigneeId;

    @Column(name = "status", unique = false, nullable = true)
    private String status;

    @Column(name = "close_code", unique = false, nullable = true)
    private String closeCode;

    @Column(name = "resolution", unique = false, nullable = true)
    private String resolution;

    @Column(name = "comment", unique = false, nullable = true)
    private String comment;

    @Column(name = "updated_at", unique = false, nullable = true)
    private ZonedDateTime updatedAt;

    @Column(name = "type", unique = false, nullable = true)
    private String type;

    @Column(name = "sla_start_time", unique = false, nullable = true)
    private ZonedDateTime slaStartTime;

    @Column(name = "jira_number", unique = false, nullable = true)
    private String jiraNumber;

    @Column(name = "template_name", unique = false, nullable = true)
    private String templateName;

    @Column(name = "ext_organization", unique = false, nullable = true)
    private String extOrganization;

    @Column(name = "ext_id", unique = false, nullable = true)
    private String extId;

    @Column(name = "ext_status", unique = false, nullable = true)
    private String extStatus;

    @Column(name = "ext_assignee_time", unique = false, nullable = true)
    private String extAssigneeTime;

    @Column(name = "source", unique = false, nullable = true)
    private String source;

    @Column(name = "specialist_id", unique = false, nullable = true)
    private String specialistId;

    @Column(name = "priority", unique = false, nullable = true)
    private String priority;

    @Column(name = "idented_at", unique = false, nullable = true)
    private ZonedDateTime identedAt;

    @Column(name = "fact_end_at", unique = false, nullable = true)
    private ZonedDateTime factEndAt;

    @Column(name = "fact_begin_at", unique = false, nullable = true)
    private ZonedDateTime factBeginAt;

    @Column(name = "notification_sent")
    private Boolean notificationSent;

    public Incidents(){}

    public Incidents(int id, String incidentId, ZonedDateTime createdAt, ZonedDateTime expiredAt, int authorId, int contactId, int serviceId, String category, String failurePoint, int configurationItemId, String title, String description, String impact, String severity, int groupId, String assigneeId, String status, String closeCode, String resolution, String comment, ZonedDateTime updatedAt, String type, ZonedDateTime slaStartTime, String jiraNumber, String templateName, String extOrganization, String extId, String extStatus, String extAssigneeTime, String source, String specialistId, String priority, ZonedDateTime identedAt, ZonedDateTime factEndAt, ZonedDateTime factBeginAt, Boolean notificationSent) {
        this.id = id;
        this.incidentId = incidentId;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.authorId = authorId;
        this.contactId = contactId;
        this.serviceId = serviceId;
        this.category = category;
        this.failurePoint = failurePoint;
        this.configurationItemId = configurationItemId;
        this.title = title;
        this.description = description;
        this.impact = impact;
        this.severity = severity;
        this.groupId = groupId;
        this.assigneeId = assigneeId;
        this.status = status;
        this.closeCode = closeCode;
        this.resolution = resolution;
        this.comment = comment;
        this.updatedAt = updatedAt;
        this.type = type;
        this.slaStartTime = slaStartTime;
        this.jiraNumber = jiraNumber;
        this.templateName = templateName;
        this.extOrganization = extOrganization;
        this.extId = extId;
        this.extStatus = extStatus;
        this.extAssigneeTime = extAssigneeTime;
        this.source = source;
        this.specialistId = specialistId;
        this.priority = priority;
        this.identedAt = identedAt;
        this.factEndAt = factEndAt;
        this.factBeginAt = factBeginAt;
        this.notificationSent = notificationSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(ZonedDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFailurePoint() {
        return failurePoint;
    }

    public void setFailurePoint(String failurePoint) {
        this.failurePoint = failurePoint;
    }

    public Integer getConfigurationItemId() {
        return configurationItemId;
    }

    public void setConfigurationItemId(Integer configurationItemId) {
        this.configurationItemId = configurationItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCloseCode() {
        return closeCode;
    }

    public void setCloseCode(String closeCode) {
        this.closeCode = closeCode;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZonedDateTime getSlaStartTime() {
        return slaStartTime;
    }

    public void setSlaStartTime(ZonedDateTime slaStartTime) {
        this.slaStartTime = slaStartTime;
    }

    public String getJiraNumber() {
        return jiraNumber;
    }

    public void setJiraNumber(String jiraNumber) {
        this.jiraNumber = jiraNumber;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getExtOrganization() {
        return extOrganization;
    }

    public void setExtOrganization(String extOrganization) {
        this.extOrganization = extOrganization;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getExtStatus() {
        return extStatus;
    }

    public void setExtStatus(String extStatus) {
        this.extStatus = extStatus;
    }

    public String getExtAssigneeTime() {
        return extAssigneeTime;
    }

    public void setExtAssigneeTime(String extAssigneeTime) {
        this.extAssigneeTime = extAssigneeTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public ZonedDateTime getIdentedAt() {
        return identedAt;
    }

    public void setIdentedAt(ZonedDateTime identedAt) {
        this.identedAt = identedAt;
    }

    public ZonedDateTime getFactEndAt() {
        return factEndAt;
    }

    public void setFactEndAt(ZonedDateTime factEndAt) {
        this.factEndAt = factEndAt;
    }

    public ZonedDateTime getFactBeginAt() {
        return factBeginAt;
    }

    public void setFactBeginAt(ZonedDateTime factBeginAt) {
        this.factBeginAt = factBeginAt;
    }

    public Boolean getNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(Boolean notificationSent) {
        this.notificationSent = notificationSent;
    }
}
