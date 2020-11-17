package Monitoring.Monitoring.db.vertica.repositories.interfaces;

import Monitoring.Monitoring.db.models.Updates;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

public interface SmRawdataMeasVerticaRepository {
    List<SmRawdataMeasVertica> getSmRawdataMeasVertica(Updates lastUpdate) throws SQLException;
}
