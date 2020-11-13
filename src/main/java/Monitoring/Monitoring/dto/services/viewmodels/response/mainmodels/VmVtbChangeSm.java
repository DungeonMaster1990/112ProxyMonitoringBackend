package Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VmVtbChangeSm implements VmBaseSmModel<VmVtbChangeSm> {
    private String affectedItem;
    private String[] backoutPlan;
    private Boolean isManagerNotification;
    private String createdBy;
    private ZonedDateTime createdAt;
    private String updatedBy;
    private ZonedDateTime updatedAt;
    private String closedBy;
    private ZonedDateTime closedAt;
    private String riskLevel;
    private String criticality;
    private String changeOwner;
    private String[] buildTestPlan;
    private String[] buildTestResult;
    private String[] affectedCis;
    private String[] affectedIts;
    private String initialImpact;
    private String riskFinance;
    private String wasLoadingTesting;

    public Class<VmVtbChangeSm> getUClass(){ return VmVtbChangeSm.class; }


}