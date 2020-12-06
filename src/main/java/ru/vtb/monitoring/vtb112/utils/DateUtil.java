package ru.vtb.monitoring.vtb112.utils;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class DateUtil {

    private DateUtil() {
    }

    public static ZonedDateTime dbDateToZonedDate(Timestamp dbDate, String zonedId) {
        return ZonedDateTime.ofInstant(dbDate.toInstant(), ZoneId.of(zonedId));
    }

    public static ZonedDateTime now() {
        Clock cl = Clock.systemUTC();
        return ZonedDateTime.now(cl);
    }
}
