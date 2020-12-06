package ru.vtb.monitoring.vtb112.dto.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlPlanStatusType;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmPlanResponse {

    @ApiModelProperty(value = "Идентификатор плановой работы", example = "1")
    private String id;
    @ApiModelProperty(value = "Имя плановой работы", example = "Изменение IM-283501")
    private String name;
    @ApiModelProperty(value = "Статус плановой работы", example = "Согласование")
    private String status;
    @ApiModelProperty(value = "Тип статуса плановой работы")
    private BlPlanStatusType statusType;
    @ApiModelProperty(value = "Описание плановой работы", example = "Описание")
    private String description;
    @ApiModelProperty(value = "Затронутые системы", example = "[\"Платежи\",\"Переводы\"]")
    private Set<String> affectedSystems;
    @ApiModelProperty(value = "Плановое начало", example = "2020-06-26T18:31:42Z")
    private ZonedDateTime startDate;

}