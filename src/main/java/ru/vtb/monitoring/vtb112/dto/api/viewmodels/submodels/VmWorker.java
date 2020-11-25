package ru.vtb.monitoring.vtb112.dto.api.viewmodels.submodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.BlWorkerStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmWorker {

    private String name;
    private BlWorkerStatus status;
    private String role;

}
