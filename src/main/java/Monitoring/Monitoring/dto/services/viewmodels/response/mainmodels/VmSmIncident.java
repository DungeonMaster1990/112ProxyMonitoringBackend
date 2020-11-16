package Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmIncident {
    @JsonAlias("Id")
    private String id;
    @JsonAlias("CreatedAt")
    private ZonedDateTime createdAt;
    @JsonAlias("ExpiredAt")
    private ZonedDateTime expiredAt;
    @JsonAlias("AuthorId")
    private Integer authorId;
    @JsonAlias("ContactId")
    private Integer contactId;
    @JsonAlias("ServiceId")
    private Integer serviceId;
    @JsonAlias("Category")
    private String category;
    @JsonAlias("FailurePoint")
    private String failurePoint;
    @JsonAlias("ConfigurationItemId")
    private Integer configurationItemId;
    @JsonAlias("Title")
    private String title;
    @JsonAlias("Description")
    private String description;
    @JsonAlias("Impact")
    private String impact;
    @JsonAlias("Severity")
    private String severity;
    @JsonAlias("GroupId")
    private Integer groupId;
    @JsonAlias("AssigneeId")
    private String assigneeId;
    @JsonAlias("Status")
    private String status;
    @JsonAlias("CloseCode")
    private String closeCode;
    @JsonAlias("Resolution")
    private String resolution;
    @JsonAlias("Comment")
    private String comment;
    @JsonAlias("UpdatedAt")
    private ZonedDateTime updatedAt;
    @JsonAlias("Type")
    private String type;
    @JsonAlias("SlaStartTime")
    private ZonedDateTime slaStartTime;
    @JsonAlias("JiraNumber")
    private String jiraNumber;
    @JsonAlias("TemplateName")
    private String templateName;
    @JsonAlias("ExtOrganization")
    private String extOrganization;
    @JsonAlias("ExtId")
    private String extId;
    @JsonAlias("ExtStatus")
    private String extStatus;
    @JsonAlias("ExtAssigneeTime")
    private String extAssigneeTime;
    @JsonAlias("Source")
    private String source;
    @JsonAlias("SpecialistId")
    private String specialistId;
    @JsonAlias("Priority")
    private String priority;
    @JsonAlias("IdentedAt")
    private ZonedDateTime identedAt;
    @JsonAlias("FactEndAt")
    private ZonedDateTime factEndAt;
    @JsonAlias("FactBeginAt")
    private ZonedDateTime factBeginAt;

    public VmSmIncident(String id, ZonedDateTime createdAt, ZonedDateTime expiredAt, Integer authorId, Integer contactId, Integer serviceId, String category, String failurePoint, Integer configurationItemId, String title, String description, String impact, String severity, Integer groupId, String assigneeId, String status, String closeCode, String resolution, String comment, ZonedDateTime updatedAt, String type, ZonedDateTime slaStartTime, String jiraNumber, String templateName, String extOrganization, String extId, String extStatus, String extAssigneeTime, String source, String specialistId, String priority, ZonedDateTime identedAt, ZonedDateTime factEndAt, ZonedDateTime factBeginAt) {
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
