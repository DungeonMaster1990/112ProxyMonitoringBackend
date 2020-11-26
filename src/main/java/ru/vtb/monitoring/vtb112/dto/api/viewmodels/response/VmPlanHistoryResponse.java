package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.submodels.VmHistoryRecord;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmPlanHistoryResponse {
    private VmHistoryRecord[] completedHistory;
    private VmHistoryRecord[] planHistory;
}
