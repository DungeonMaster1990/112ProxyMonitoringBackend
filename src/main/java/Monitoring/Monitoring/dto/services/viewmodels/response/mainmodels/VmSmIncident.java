package Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
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
    private String[] description;
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

    public VmSmIncident(String id, ZonedDateTime createdAt, ZonedDateTime expiredAt, Integer authorId, Integer contactId, Integer serviceId, String category, String failurePoint, Integer configurationItemId, String title, String[] description, String impact, String severity, Integer groupId, String assigneeId, String status, String closeCode, String resolution, String comment, ZonedDateTime updatedAt, String type, ZonedDateTime slaStartTime, String jiraNumber, String templateName, String extOrganization, String extId, String extStatus, String extAssigneeTime, String source, String specialistId, String priority, ZonedDateTime identedAt, ZonedDateTime factEndAt, ZonedDateTime factBeginAt) {
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
}
