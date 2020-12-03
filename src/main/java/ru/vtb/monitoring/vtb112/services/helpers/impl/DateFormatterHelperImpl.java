package ru.vtb.monitoring.vtb112.services.helpers.impl;

import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.services.helpers.interfaces.DateFormatterHelper;

import java.sql.Timestamp;
import java.time.*;

@Component
public class DateFormatterHelperImpl implements DateFormatterHelper {
    @Override
    public ZonedDateTime dbDateToZonedDate(Timestamp dbDate, String zonedId) {
        return ZonedDateTime.ofInstant(dbDate.toInstant(), ZoneId.of(zonedId));
    }

    @Override
    public ZonedDateTime dbDateToZonedDate(ZonedDateTime dbDate, String fromZonedId, String toZonedId) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(dbDate.toInstant(), ZoneId.of(toZonedId));
        return ZonedDateTime.of(localDateTime, ZoneId.of(fromZonedId));
    }
}
