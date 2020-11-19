package Monitoring.Monitoring.dto.api.viewmodels.enums;

public enum BlMetricsStatus {
    normal,
    warning;

    public static BlMetricsStatus resolve(int status) {
        return status == 0 ? normal : warning;
    }
}
