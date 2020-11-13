package Monitoring.Monitoring.db.models;

import java.time.ZonedDateTime;

public interface BaseSmModel<T> {
    ZonedDateTime getUpdatedAt();
    Class<T> getTClass();
}
