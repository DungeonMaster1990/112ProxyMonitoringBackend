package Monitoring.Monitoring.db.vertica.repositories.interfaces;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface SmDefMeasurementVerticaRepository {
    List<SmDefMeasurementVertica> getSmDefMeasurements(List<Metrics> metricsIds) throws SQLException;
}
