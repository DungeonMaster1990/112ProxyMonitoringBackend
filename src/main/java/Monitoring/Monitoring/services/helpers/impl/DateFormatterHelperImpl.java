package Monitoring.Monitoring.services.helpers.impl;

import Monitoring.Monitoring.services.helpers.interfaces.DateFormatterHelper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatterHelperImpl implements DateFormatterHelper {
    @Override
    public ZonedDateTime dbDateToZonedDate(Timestamp dbDate) {
        return  ZonedDateTime.ofInstant(dbDate.toInstant(), ZoneId.of("UTC"));
    }
}
