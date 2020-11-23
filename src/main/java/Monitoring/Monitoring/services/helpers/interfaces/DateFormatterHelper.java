package Monitoring.Monitoring.services.helpers.interfaces;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Component
public interface DateFormatterHelper {
    ZonedDateTime dbDateToZonedDate(Timestamp dbDate);
}
