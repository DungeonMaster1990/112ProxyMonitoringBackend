package Monitoring.Monitoring.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="changes", schema = "monitoring")
public class Changes implements BaseSmModel {
    @Id
    @GenericGenerator(
            name = "changesIdGenerator",
            strategy = "sequence-identity",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence",
                            value = "monitoring.changes_id_seq")
            }
    )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "changesIdGenerator")
    private Integer id;
    //main object
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
    @Column(name = "closed_by")
    private String closedBy;
    @Column(name = "closed_at")
    private ZonedDateTime closedAt;
    @Column(name = "affected_item")
    private String affectedItem;
    @Column(name = "backout_plan")
    private String[] backoutPlan;
    @Column(name = "is_manager_notification")
    private Boolean isManagerNotification;
    @Column(name = "risk_level")
    private String riskLevel;
    @Column(name = "criticality")
    private String criticality;
    @Column(name = "change_owner")
    private String changeOwner;
    @Column(name = "build_test_plan")
    private String buildTestPlan;
    @Column(name = "build_test_result")
    private String buildTestResult;
    @Column(name = "affected_cis")
    private String affectedCis;
    @Column(name = "affected_its")
    private String affectedIts;
    @Column(name = "initial_impact")
    private String initialImpact;
    @Column(name = "risk_finance")
    private String riskFinance;
    @Column(name = "was_loading_testing")
    private String wasLoadingTesting;
    @Column(name = "func_testing")
    private  String funcTesting;
    @Column(name = "sys_impact")
    private String sysImpact;
    @Column(name = "custom_reach")
    private String customReach;
    @Column(name = "reserve_system")
    private String reserveSystem;
    @Column(name = "rollback_duration")
    private String rollbackDuration;
    @Column(name = "pr_window_time")
    private String prWindowTime;
    @Column(name = "freeze_time")
    private String freezeTime;
    @Column(name = "change_failure")
    private String changeFailure;
    @Column(name = "sensitive_infr_impact")
    private String sensitiveInfrImpact;
    @Column(name = "implementation_start_at")
    private ZonedDateTime implementationStartAt;
    @Column(name = "implementation_end_at")
    private ZonedDateTime implementationEndAt;
    @Column(name = "resolution_code")
    private String resolutionCode;
    @Column(name = "is_no_downtime")
    private Boolean isNoDowntime;
    @Column(name = "is_st_chg_candidat")
    private  Boolean isStChgCandidat;
    @Column(name = "is_have_report")
    private Boolean isHaveReport;
    @Column(name = "template_id")
    private String templateId;
    @Column(name = "template_name")
    private String templateName;
    @Column(name = "fault_category")
    private String faultCategory;
    @Column(name = "reason_no_cab")
    private String reasonNoCab;
    //header
    @Column(name = "change_id")
    private String changeId;
    @Column(name = "current_phase")
    private String currentPhase;
    @Column(name = "status")
    private String status;
    @Column(name = "category")
    private String category;
    @Column(name = "subcategory")
    private String subcategory;
    @Column(name = "brief_description")
    private String briefDescription;
    @Column(name = "requested_by")
    private String requestedBy;
    @Column(name = "requested_for")
    private String requestedFor;
    @Column(name = "assign_dept")
    private String assignDept;
    @Column(name = "planned_start_at")
    private ZonedDateTime plannedStartAt;
    @Column(name = "planned_end_at")
    private ZonedDateTime plannedEndAt;
    @Column(name = "foreign_id")
    private String foreignId;
    //description
    @Column(name = "description")
    private String description;
    @Column(name = "justification")
    private String justification;
    @Column(name = "acceptance_comments")
    private String acceptanceComments;
    @Column(name = "plan")
    private String plan;
    @Column(name = "vtb_risk_description")
    private String vtbRiskDescription;
    //middle
    @Column(name = "sched_outage_start_at")
    private ZonedDateTime schedOutageStartAt;
    @Column(name = "sched_outage_end_at")
    private ZonedDateTime schedOutageEndAt;
    @Column(name = "assets")
    private String assets;
    @Column(name = "affected_services")
    private String affectedServices;
    @Column(name = "down_start_at")
    private ZonedDateTime downStartAt;
    @Column(name = "down_end_at")
    private ZonedDateTime downEndAt;
    //close
    @Column(name = "closing_comments")
    private String closingComments;
}
