package ru.vtb.monitoring.vtb112.dto.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.submodels.VmManager;
import ru.vtb.monitoring.vtb112.dto.api.submodels.VmWorker;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VmAccidentWorkersResponse {

    private VmManager manager;
    private List<VmWorker> workers;

}
