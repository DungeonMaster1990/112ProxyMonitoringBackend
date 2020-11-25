package ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums;

public enum BlMetricsStatus {
    normal,
    warning;

    public static BlMetricsStatus resolve(int status) {
        return status == 0 ? normal : warning;
    }
}
