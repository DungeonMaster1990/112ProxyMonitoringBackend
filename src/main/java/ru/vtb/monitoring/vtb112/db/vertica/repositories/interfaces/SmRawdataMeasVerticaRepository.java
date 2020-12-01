package ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Metrics;
import ru.vtb.monitoring.vtb112.db.models.Updates;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface SmRawdataMeasVerticaRepository {
    List<SmRawdataMeasVertica> getSmRawdataMeasVertica(Updates lastUpdate, List<Metrics> metrics) throws SQLException;
}
