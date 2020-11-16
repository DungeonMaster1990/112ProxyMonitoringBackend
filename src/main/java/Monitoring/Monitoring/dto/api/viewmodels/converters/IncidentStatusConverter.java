package Monitoring.Monitoring.dto.api.viewmodels.converters;

import Monitoring.Monitoring.db.models.enums.Status;
import Monitoring.Monitoring.dto.api.viewmodels.enums.BlAccidentStatusType;

import java.time.ZonedDateTime;

public class IncidentStatusConverter {

    public static String convertToStatus(Status status,
                                         ZonedDateTime isFactEndAt,
                                         ZonedDateTime predictedAt) {
        if (status == null) {
            return null;
        }
        if (status.equals(Status.in_process) && isFactEndAt != null && predictedAt != null) {
            return "устранение последствий";
        }
        return status.getStatus();

    }

    public static BlAccidentStatusType convertToStatusType(Status status) {
        if (status == null) {
            return null;
        }
        return switch (status) {
            case assigned -> BlAccidentStatusType.critical;
            case in_process -> BlAccidentStatusType.warning;
            default -> BlAccidentStatusType.normal;
        };
    }

}
