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
public class VmAccidentInfoResponse {
    @ApiModelProperty(example = "'1'")
    private String id;
    @ApiModelProperty(example = "IM-000000000'")
    private String name;
    @ApiModelProperty(example = "1")
    private Integer category;
    @ApiModelProperty(example = "В работе")
    private String status;
    @ApiModelProperty(example = "normal")
    private BlAccidentStatusType statusType;
    private String description;
    private String impactDescription;
    private String failurePoint;
    private String configurationUnit;
    private List<String> affectedSystems;
    private ZonedDateTime startDate;
    private ZonedDateTime detectionDate;
    private ZonedDateTime predictDate;
    private String conferenceLink;
    private String telegramLink;
}
