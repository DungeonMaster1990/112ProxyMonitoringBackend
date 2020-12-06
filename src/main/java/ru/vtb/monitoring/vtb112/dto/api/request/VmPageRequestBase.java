package ru.vtb.monitoring.vtb112.dto.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VmPageRequestBase {
    @ApiModelProperty(value = "Ограничение на количество выдачи элементов в порции", example = "10")
    @Min(value = 1, message = "Limit should not be less than 1")
    private int limit;
    @ApiModelProperty(value = "Страница для порционной выдачи", example = "1")
    @Min(value = 1, message = "Page should not be less than 1")
    private int page;
}
