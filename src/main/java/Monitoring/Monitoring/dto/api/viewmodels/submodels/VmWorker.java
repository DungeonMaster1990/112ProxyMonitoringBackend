package Monitoring.Monitoring.dto.api.viewmodels.submodels;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlWorkerStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmWorker {

    private String name;
    private BlWorkerStatus status;
    private String role;

}
