package Monitoring.Monitoring.dto.api.viewmodels.response;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlMetricsStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VmMetricsResponse {
    private String          id;
    private String          name;
    private Boolean         mine;
    private String          value;
    private long            delta;
    private double          deltaPercent;
    private BlMetricsStatus deltaStatus;
    private double          totalPercent;
}
