package ru.vtb.monitoring.vtb112.services.helpers.impl;

import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.services.helpers.interfaces.DateFormatterHelper;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class DateFormatterHelperImpl implements DateFormatterHelper {
    @Override
    public ZonedDateTime dbDateToZonedDate(Timestamp dbDate, String zonedId) {
        return ZonedDateTime.ofInstant(dbDate.toInstant(), ZoneId.of(zonedId));
    }
}
