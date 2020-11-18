package Monitoring.Monitoring.db.models;

import Monitoring.Monitoring.db.models.converters.IncidentStatusConverter;
import Monitoring.Monitoring.db.models.enums.Status;
import Monitoring.Monitoring.db.models.enums.StatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="incidents", schema = "monitoring")
@NoArgsConstructor
public class Incident implements BaseSmModel {

    @Id
    @GeneratedValue(generator = "monitoring.incidents_id_seq")
    private Integer id;

    @Column(name = "incident_id", nullable = false)
    private String incidentId;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "expired_at")
    private ZonedDateTime expiredAt;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "contact_id")
    private Integer contactId;

    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "category")
    private Integer category;

    @Column(name = "failure_point")
    private String failurePoint;

    @Column(name = "configuration_item_id")
    private Integer configurationItemId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "impact")
    private String impact;

    @Column(name = "severity")
    private String severity;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "assignee_id")
    private String assigneeId;

    @Convert(converter = IncidentStatusConverter.class)
    @Column(name = "status")
    private Status status;

    @Column(name = "close_code")
    private String closeCode;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "comment")
    private String comment;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "type")
    private StatusType statusType;

    @Column(name = "sla_start_time")
    private ZonedDateTime slaStartTime;

    @Column(name = "jira_number")
    private String jiraNumber;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "ext_organization")
    private String extOrganization;

    @Column(name = "ext_id")
    private String extId;

    @Column(name = "ext_status")
    private String extStatus;

    @Column(name = "ext_assignee_time")
    private String extAssigneeTime;

    @Column(name = "source")
    private String source;

    @Column(name = "specialist_id")
    private String specialistId;

    @Column(name = "priority")
    private String priority;

    @Column(name = "idented_at")
    private ZonedDateTime identedAt;

    @Column(name = "fact_end_at")
    private ZonedDateTime factEndAt;

    @Column(name = "fact_begin_at")
    private ZonedDateTime factBeginAt;

    @Column(name = "notification_sent")
    private Boolean notificationSent;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="accidentid")
    private List<AffectedSystem> affectedSystems;
}
