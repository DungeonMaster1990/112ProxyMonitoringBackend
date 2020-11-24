package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import ru.vtb.monitoring.vtb112.dto.api.viewmodels.submodels.VmHistoryRecord;

public class VmPlanHistoryResponse {
    private VmHistoryRecord[] completedHistory;
    private VmHistoryRecord[] planHistory;

    public VmPlanHistoryResponse(VmHistoryRecord[] completedHistory, VmHistoryRecord[] planHistory) {
        this.completedHistory = completedHistory;
        this.planHistory = planHistory;
    }

    public VmPlanHistoryResponse(){}

    public VmHistoryRecord[] getCompletedHistory() {
        return completedHistory;
    }

    public void setCompletedHistory(VmHistoryRecord[] completedHistory) {
        this.completedHistory = completedHistory;
    }

    public VmHistoryRecord[] getPlanHistory() {
        return planHistory;
    }

    public void setPlanHistory(VmHistoryRecord[] planHistory) {
        this.planHistory = planHistory;
    }
}
