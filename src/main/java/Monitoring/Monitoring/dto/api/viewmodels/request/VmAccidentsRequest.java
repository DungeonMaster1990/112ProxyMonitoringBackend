package Monitoring.Monitoring.dto.api.viewmodels.request;

import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

public class VmAccidentsRequest extends VmPageRequestBase {
    private ZonedDateTime startDate;
    private String[] affectedSystems;
    private String[] failurePoints;
    @Nullable
    private Long systemId;
    @Nullable
    private int planTypeId;
    private String keyword;

    public VmAccidentsRequest(ZonedDateTime startDate, String[] affectedSystems, String[] failurePoints, Long systemId, int planTypeId, String keyword, int limit, int page) {
        super(limit, page);
        this.startDate = startDate;
        this.affectedSystems = affectedSystems;
        this.failurePoints = failurePoints;
        this.systemId = systemId;
        this.planTypeId = planTypeId;
        this.keyword = keyword;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public String[] getAffectedSystems() {
        return affectedSystems;
    }

    public void setAffectedSystems(String[] affectedSystems) {
        this.affectedSystems = affectedSystems;
    }

    public String[] getFailurePoints() {
        return failurePoints;
    }

    public void setFailurePoints(String[] failurePoints) {
        this.failurePoints = failurePoints;
    }

    @Nullable
    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(@Nullable Long systemId) {
        this.systemId = systemId;
    }

    public int getPlanTypeId() {
        return planTypeId;
    }

    public void setPlanTypeId(int planTypeId) {
        this.planTypeId = planTypeId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
