package Monitoring.Monitoring.dto.services.viewmodels.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VmIncidentResponse {
    private String id;
    private ZonedDateTime createdAt;
    private ZonedDateTime expiredAt;
    private int authorId;
    private int contactId;
    private int serviceId;
    private String category;
    private String failurePoint;
    private int configurationItemId;
    private String title;
    private String description;
    private String impact;
    private String severity;
    private int groupId;
    private String assigneeId;
    private String status;
    private String closeCode;
    private String resolution;
    private String comment;
    private ZonedDateTime updatedAt;
    private String type;
    private ZonedDateTime slaStartTime;
    private String jiraNumber;
    private String templateName;
    private String extOrganization;
    private String extId;
    private String extStatus;
    private String extAssigneeTime;
    private String source;
    private String specialistId;
    private String priority;
    private ZonedDateTime identedAt;
    private ZonedDateTime factEndAt;
    private ZonedDateTime factBeginAt;

    public VmIncidentResponse(String id, ZonedDateTime createdAt, ZonedDateTime expiredAt, int authorId, int contactId, int serviceId, String category, String failurePoint, int configurationItemId, String title, String description, String impact, String severity, int groupId, String assigneeId, String status, String closeCode, String resolution, String comment, ZonedDateTime updatedAt, String type, ZonedDateTime slaStartTime, String jiraNumber, String templateName, String extOrganization, String extId, String extStatus, String extAssigneeTime, String source, String specialistId, String priority, ZonedDateTime identedAt, ZonedDateTime factEndAt, ZonedDateTime factBeginAt) {
        this.id = id;
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
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
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

    public int getConfigurationItemId() {
        return configurationItemId;
    }

    public void setConfigurationItemId(int configurationItemId) {
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
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
}
