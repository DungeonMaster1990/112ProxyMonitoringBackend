package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.BlPlanStatusType;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmPlanResponse {

    private String id;
    private String name;
    private String status;
    private BlPlanStatusType statusType;
    private String description;
    private List<String> affectedSystems;
    private ZonedDateTime startDate;

}
