package Monitoring.Monitoring.db.vertica.repositories.interfaces;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

public interface SmDefMeasurementVerticaRepository {
    List<SmDefMeasurementVertica> getSmDefMeasurements(List<Metrics> metricsIds) throws SQLException;
}
