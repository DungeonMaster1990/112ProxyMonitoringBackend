package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.submodels.VmSmChangeClose;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.submodels.VmSmChangeDescription;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.submodels.VmSmChangeHeader;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.submodels.VmSmChangeMiddle;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmChange implements VmBaseModel {
    @JsonAlias("CreatedBy")
    private String createdBy;
    @JsonAlias("CreatedAt")
    private ZonedDateTime createdAt;
    @JsonAlias("UpdatedBy")
    private String updatedBy;
    @JsonAlias("UpdatedAt")
    private ZonedDateTime updatedAt;
    @JsonAlias("ClosedBy")
    private String closedBy;
    @JsonAlias("ClosedAt")
    private ZonedDateTime closedAt;
    @JsonAlias("AffectedItem")
    private String affectedItem;
    @JsonAlias("BackoutPlans")
    private String[] backoutPlan;
    @JsonAlias("IsManagerNotification")
    private Boolean isManagerNotification;
    @JsonAlias("RiskLevel")
    private String riskLevel;
    @JsonAlias("Criticality")
    private String criticality;
    @JsonAlias("ChangeOwner")
    private String changeOwner;
    @JsonAlias("BuildTestPlan")
    private String[] buildTestPlan;
    @JsonAlias("BuildTestResult")
    private String[] buildTestResult;
    @JsonAlias("AffectedCis")
    private String[] affectedCis;
    @JsonAlias("AffectedIts")
    private String[] affectedIts;
    @JsonAlias("InitialImpact")
    private String initialImpact;
    @JsonAlias("RiskFinance")
    private String riskFinance;
    @JsonAlias("WasLoadingTesting")
    private String wasLoadingTesting;
    @JsonAlias("FuncTesting")
    private  String funcTesting;
    @JsonAlias("SysImpact")
    private String sysImpact;
    @JsonAlias("CustomReach")
    private String customReach;
    @JsonAlias("ReserveSystem")
    private String reserveSystem;
    @JsonAlias("RollbackDuration")
    private String rollbackDuration;
    @JsonAlias("PrWindowTime")
    private String prWindowTime;
    @JsonAlias("FreezeTime")
    private String freezeTime;
    @JsonAlias("ChangeFailure")
    private String changeFailure;
    @JsonAlias("SensitiveInfrImpact")
    private String sensitiveInfrImpact;
    @JsonAlias("ImplementationStartAt")
    private ZonedDateTime implementationStartAt;
    @JsonAlias("ImplementationEndAt")
    private ZonedDateTime implementationEndAt;
    @JsonAlias("ResolutionCode")
    private String resolutionCode;
    @JsonAlias("IsNoDowntime")
    private Boolean isNoDowntime;
    @JsonAlias("IsStChgCandidat")
    private  Boolean isStChgCandidat;
    @JsonAlias("IsHaveReport")
    private Boolean isHaveReport;
    @JsonAlias("TemplateId")
    private String templateId;
    @JsonAlias("TemplateName")
    private String templateName;
    @JsonAlias("FaultCategory")
    private String faultCategory;
    @JsonAlias("reasonNoCab")
    private String reasonNoCab;
    @JsonAlias("header")
    private VmSmChangeHeader header;
    @JsonAlias("description.structure")
    private VmSmChangeDescription description;
    @JsonAlias("middle")
    private VmSmChangeMiddle middle;
    @JsonAlias("close")
    private VmSmChangeClose close;
}