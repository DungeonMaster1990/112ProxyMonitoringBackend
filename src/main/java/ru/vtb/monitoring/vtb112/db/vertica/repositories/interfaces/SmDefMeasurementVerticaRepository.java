package ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Metrics;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmDefMeasurementVertica;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface SmDefMeasurementVerticaRepository {
    List<SmDefMeasurementVertica> getSmDefMeasurements(List<Metrics> metricsIds) throws SQLException;
}
