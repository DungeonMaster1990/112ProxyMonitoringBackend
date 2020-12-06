package ru.vtb.monitoring.vtb112.dto.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmNewAccidentResponse {
    @ApiModelProperty(example = "'1'")
    private String id;
    @ApiModelProperty(example = "'IM-000000000'")
    private String name;
}
