package Monitoring.Monitoring.dto.api.viewmodels.response;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlMetricsStatus;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class VmMetricInfoResponse {
    private long value;
    private long delta;
    private double deltaPercent;
    private BlMetricsStatus deltaStatus;
    private ZonedDateTime date;
}
