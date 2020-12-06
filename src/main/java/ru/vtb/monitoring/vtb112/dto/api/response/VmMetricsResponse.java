package ru.vtb.monitoring.vtb112.dto.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlMetricsStatus;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class VmMetricsResponse {
    private String id;
    private String name;
    private Boolean mine;
    private String value;
    private long delta;
    private double deltaPercent;
    private BlMetricsStatus deltaStatus;
    private double totalPercent;
}
