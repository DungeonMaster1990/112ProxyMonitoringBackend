package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.submodels.VmManager;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.submodels.VmWorker;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmPlanWorkersResponse {
    private VmManager manager;
    private List<VmWorker> workers;
}


