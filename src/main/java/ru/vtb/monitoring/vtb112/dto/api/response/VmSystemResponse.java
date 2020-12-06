package ru.vtb.monitoring.vtb112.dto.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmSystemResponse {
    @ApiModelProperty(example = "'1'")
    private String id;
    @ApiModelProperty(example = "Все аварии")
    private String name;
    @ApiModelProperty(example = "true")
    private Boolean mine;
    @ApiModelProperty(example = "1")
    private int criticalAccidents;
    @ApiModelProperty(example = "2")
    private int majorAccidents;
    @ApiModelProperty(example = "3")
    private int minorAccidents;
}
