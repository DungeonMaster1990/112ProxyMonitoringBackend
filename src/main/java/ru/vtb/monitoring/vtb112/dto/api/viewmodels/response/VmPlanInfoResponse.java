package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.BlPlanStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmPlanInfoResponse {

    private String id;
    private String name;
    private String status;
    private BlPlanStatus statusType;
    private String description;
    private String impactDescription;
    private String degradationRate;
    private String configurationUnit;
    private List<String> affectedSystems;
    private ZonedDateTime startDate;
    private ZonedDateTime finishDate;
    private ZonedDateTime startDownDate;
    private ZonedDateTime finishDownDate;

}
