package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import ru.vtb.monitoring.vtb112.dto.api.viewmodels.submodels.VmHistoryRecord;

public class VmAccidentHistoryResponse {
    private VmHistoryRecord[] completedHistory;
    private VmHistoryRecord[] planHistory;

    public VmAccidentHistoryResponse(VmHistoryRecord[] completedHistory, VmHistoryRecord[] planHistory) {
        this.completedHistory = completedHistory;
        this.planHistory = planHistory;
    }

    public VmAccidentHistoryResponse(){}

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
