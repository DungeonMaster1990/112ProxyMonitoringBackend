package dto.ViewModels.Response;

import dto.ViewModels.SubModels.VmHistoryRecord;
import org.springframework.beans.factory.annotation.Autowired;

public class VmAccidentHistoryResponse {
    private VmHistoryRecord[] completedHistory;
    private VmHistoryRecord[] planHistory;

    public VmAccidentHistoryResponse(VmHistoryRecord[] completedHistory, VmHistoryRecord[] planHistory) {
        this.completedHistory = completedHistory;
        this.planHistory = planHistory;
    }

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
