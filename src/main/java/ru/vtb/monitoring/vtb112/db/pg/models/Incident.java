package ru.vtb.monitoring.vtb112.db.pg.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlAccidentStatusType;

import javax.persistence.*;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incidents", schema = "monitoring")
public class Incident implements BaseSmModel, Serializable {

    private static final Pattern CONFERENCE_LINK_PATTERN = Pattern.compile("(?<=#)([^#].+?)#+");

    @Id
    @GenericGenerator(
            name = "incidentIdGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "monitoring.incidents_id_seq")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incidentIdGenerator")
    private Integer id;

    @Column(name = "incident_id", nullable = false)
    private String incidentId;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "expired_at")
    private ZonedDateTime expiredAt;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "contact_id")
    private String contactId;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "category")
    private String category;

    @Column(name = "failure_point")
    private String failurePoint;

    @Column(name = "configuration_item_id")
    private String configurationItemId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "impact")
    private String impact;

    @Column(name = "severity")
    private String severity;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "assignee_id")
    private String assigneeId;

    @Column(name = "status")
    private String status;

    @Column(name = "close_code")
    private String closeCode;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "comment")
    private String comment;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

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

    @Column(name = "manager_id")
    private String managerId;

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

    @Column(name = "consequences")
    private String consequences;

    @Column(name = "elimination_consequences_at")
    private ZonedDateTime eliminationConsequencesAt;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "incident", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Unavailabilities> unavailabilities = new ArrayList<>();

    public void setManagerId(String managerId) {
        if (this.managerId == null) {
            this.managerId = managerId;
        }
    }

    public int getPriorityAsCategory() {
        if (priority == null) {
            return 1;
        }
        try {
            return Integer.parseInt(priority);
        } catch (NumberFormatException ignored) {
            return 1;
        }
    }

    public String getStatus() {
        if ("Назначено".equals(status) && eliminationConsequencesAt != null) {
            return "Устранение последствий";
        }
        return status;
    }

    public BlAccidentStatusType getStatusType() {
        if (status == null) {
            return BlAccidentStatusType.normal;
        }
        return switch (status) {
            case "Назначено" -> BlAccidentStatusType.critical;
            case "В работе" -> BlAccidentStatusType.warning;
            default -> BlAccidentStatusType.normal;
        };
    }

    public String getConferenceLink() {
        if (resolution == null) return null;
        Matcher matcher = CONFERENCE_LINK_PATTERN.matcher(resolution);
        while (matcher.find()) {
            String url = matcher.group(1);
            if (url != null && !url.isBlank()) {
                try {
                    url = url.strip();
                    new URL(url);
                    return url;
                } catch (MalformedURLException ignored) {
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident = (Incident) o;
        return incidentId.equals(incident.incidentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidentId);
    }
}
