package ru.vtb.monitoring.vtb112.dto.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlAccidentStatusType;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmAccidentResponse {
    @ApiModelProperty(example = "'1'")
    private String id;
    @ApiModelProperty(example = "IM-000000000")
    private String name;
    @ApiModelProperty(example = "1")
    private int category;
    @ApiModelProperty(example = "В работе")
    private String status;
    @ApiModelProperty(example = "critical")
    private BlAccidentStatusType statusType;
    @ApiModelProperty(example = "Ошибка при работе в АБС ВТБ Фронт-ФЛ.")
    private String description;
    @ApiModelProperty(example = "['Все аварии']")
    private List<String> affectedSystems;
    private ZonedDateTime detectionDate;
}
