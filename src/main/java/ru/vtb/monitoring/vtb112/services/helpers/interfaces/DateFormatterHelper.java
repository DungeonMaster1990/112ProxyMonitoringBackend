package ru.vtb.monitoring.vtb112.services.helpers.interfaces;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Component
public interface DateFormatterHelper {
    ZonedDateTime dbDateToZonedDate(Timestamp dbDate, String zonedId);
    ZonedDateTime dbDateToZonedDate(ZonedDateTime dbDate, String fromZonedId, String toZonedId);
}
