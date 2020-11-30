package ru.vtb.monitoring.vtb112.dto.api.viewmodels.converters;

import ru.vtb.monitoring.vtb112.db.models.enums.Status;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.BlAccidentStatusType;

import java.time.ZonedDateTime;

public final class IncidentStatusConverter {

    private IncidentStatusConverter() {
    }

    public static String convertToStatus(Status status,
                                         ZonedDateTime isFactEndAt,
                                         ZonedDateTime predictedAt) {
        if (status == null) {
            return null;
        }
        if (status.equals(Status.IN_PROCESS) && (isFactEndAt != null || predictedAt != null)) {
            return "устранение последствий";
        }
        return status.getText();

    }

    public static BlAccidentStatusType convertToStatusType(Status status) {
        if (status == null) {
            return null;
        }
        return switch (status) {
            case ASSIGNED -> BlAccidentStatusType.critical;
            case IN_PROCESS -> BlAccidentStatusType.warning;
            default -> BlAccidentStatusType.normal;
        };
    }

}
