package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VmPageRequestBase {
    @ApiModelProperty(value = "Ограничение на количество выдачи элементов в порции", example = "10")
    private int limit;
    @ApiModelProperty(value = "Страница для порционной выдачи", example = "1")
    private int page;
}
