package ru.vtb.monitoring.vtb112.dto.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VmPlanSectionRequest {

    @Nullable
    @ApiModelProperty(value = "Дата начала плановой работы", example = "2020-06-26T18:00:00Z")
    private ZonedDateTime startDate;
    @Nullable
    @ApiModelProperty(value = "Дата окончания плановой работы", example = "2020-06-26T19:00:00Z")
    private ZonedDateTime finishDate;

}
