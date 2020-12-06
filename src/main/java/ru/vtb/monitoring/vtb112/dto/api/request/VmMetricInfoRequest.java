package ru.vtb.monitoring.vtb112.dto.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VmMetricInfoRequest {
    @ApiModelProperty(example = "'1'")
    @NotNull(message = "id cannot be null")
    private String id;
    @NotNull(message = "startDate cannot be null")
    private ZonedDateTime startDate;
    @NotNull(message = "finishDate cannot be null")
    private ZonedDateTime finishDate;
}
