package ru.vtb.monitoring.vtb112.dto.sm.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmIncident implements VmBaseModel {
    @JsonAlias("Id")
    private String id;
    @JsonAlias("CreatedAt")
    private ZonedDateTime createdAt;
    @JsonAlias("ExpiredAt")
    private ZonedDateTime expiredAt;
    @JsonAlias("AuthorId")
    private String authorId;
    @JsonAlias("ContactId")
    private String contactId;
    @JsonAlias("ServiceId")
    private String serviceId;
    @JsonAlias("Category")
    private String category;
    @JsonAlias("FailurePoint")
    private String failurePoint;
    @JsonAlias("ConfigurationItemId")
    private String configurationItemId;
    @JsonAlias("Title")
    private String title;
    @JsonAlias("Description")
    private String[] description;
    @JsonAlias("Impact")
    private String impact;
    @JsonAlias("Severity")
    private String severity;
    @JsonAlias("GroupId")
    private String groupId;
    @JsonAlias("AssigneeId")
    private String assigneeId;
    @JsonAlias("Status")
    private String status;
    @JsonAlias("CloseCode")
    private String closeCode;
    @JsonAlias("Resolution")
    private String[] resolution;
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
    @JsonAlias("Consequences")
    private String[] consequences;
    @JsonAlias("EliminationConsequenceAt")
    private ZonedDateTime eliminationConsequencesAt;
}
