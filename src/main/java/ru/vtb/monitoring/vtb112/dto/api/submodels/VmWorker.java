package ru.vtb.monitoring.vtb112.dto.api.submodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlWorkerStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmWorker {

    @ApiModelProperty(example = "Виноградов C.А.")
    private String name;
    @ApiModelProperty(example = "pending")
    private BlWorkerStatus status;
    @ApiModelProperty(example = "Департамент развития инфрастуктуры")
    private String role;

}
