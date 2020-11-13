package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Metrics;

import java.util.List;

public interface MetricsRepository {
    List<Metrics> getAllMetrics();
}
