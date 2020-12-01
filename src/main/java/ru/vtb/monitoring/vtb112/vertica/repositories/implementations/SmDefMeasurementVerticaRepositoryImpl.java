package ru.vtb.monitoring.vtb112.vertica.repositories.implementations;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Metrics;
import ru.vtb.monitoring.vtb112.vertica.models.SmDefMeasurementVertica;
import ru.vtb.monitoring.vtb112.vertica.repositories.interfaces.SmDefMeasurementVerticaRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SmDefMeasurementVerticaRepositoryImpl implements SmDefMeasurementVerticaRepositoryCustom {

    @PersistenceContext(unitName = "verticaEntityManager")
    private EntityManager entityManager;

    @Override
    public List<SmDefMeasurementVertica> getSmDefMeasurements(List<Metrics> metrics) {
        var cb = entityManager.getCriteriaBuilder();
        var criteriaQuery = cb.createQuery(SmDefMeasurementVertica.class);
        var from = criteriaQuery.from(SmDefMeasurementVertica.class);

        List<Predicate> predicates = new ArrayList<>();
        metrics.forEach(metric -> predicates.add(
                cb.and(cb.equal(from.get("measurementId"), metric.getMeasurementId()),
                       cb.equal(from.get("monitorId"), metric.getMonitorId()))
                )
        );
        criteriaQuery.where(cb.or(predicates.toArray(new Predicate[0])));
        TypedQuery<SmDefMeasurementVertica> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
