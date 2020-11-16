package Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmChange {
    @JsonAlias("AffectedItem")
    private String affectedItem;
    @JsonAlias("BackoutPlans")
    private String[] backoutPlan;

    private Boolean isManagerNotification;
    private String createdBy;

    private String updatedBy;

    private String closedBy;

    private String riskLevel;
    private String criticality;
    @JsonAlias("ChangeOwner")
    private String changeOwner;
    private String[] buildTestPlan;
    private String[] buildTestResult;
    @JsonAlias("AffectedCis")
    private String[] affectedCis;
    @JsonAlias("AffectedIts")
    private String[] affectedIts;
    private String initialImpact;
    private String riskFinance;
    private String wasLoadingTesting;

    @JsonAlias("CreatedAt")
    private ZonedDateTime createdAt;
    @JsonAlias("ClosedAt")
    private ZonedDateTime closedAt;
    @JsonAlias("UpdatedAt")
    private ZonedDateTime updatedAt;


}