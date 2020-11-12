package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.Incidents;
import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.db.repositories.interfaces.MetricsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MetricsRepositoryImpl implements MetricsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Metrics> getAllMetrics() {
        TypedQuery<Metrics> metricsQuery = entityManager.createQuery("select m from metrics m", Metrics.class);
        List<Metrics> metrics = metricsQuery.getResultList();
        return metrics;
    }
}
