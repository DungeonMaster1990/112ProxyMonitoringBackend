package ru.vtb.monitoring.vtb112.dto.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.submodels.VmHistoryRecord;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmAccidentHistoryResponse {
    private VmHistoryRecord[] completedHistory;
    private VmHistoryRecord[] planHistory;
}
