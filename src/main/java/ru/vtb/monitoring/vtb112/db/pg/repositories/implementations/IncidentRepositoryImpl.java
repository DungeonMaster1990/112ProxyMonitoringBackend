package ru.vtb.monitoring.vtb112.db.pg.repositories.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepositoryCustom;
import ru.vtb.monitoring.vtb112.mappers.IncidentMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class IncidentRepositoryImpl implements IncidentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private IncidentRepository incidentRepository;

    @Autowired
    private IncidentMapper incidentMapper;

    @Override
    public List<Incident> getTimeFilteredNonSentVtbIncidents(long daysDiff) {
        String qryString = """
                select i 
                  from Incident i 
                 where i.factBeginAt >= :twoDaysBackFromNow
                   and i.notificationSent <> true
                   and i.identedAt is not null
                """;
        TypedQuery<Incident> vtbIncidentsQuery = entityManager
                .createQuery(qryString, Incident.class)
                .setParameter("twoDaysBackFromNow", ZonedDateTime.now().minusDays(daysDiff));
        return vtbIncidentsQuery.getResultList();
    }

    @Override
    @Transactional
    public void markAsNotificationSent(Set<Integer> incidentsIds) {
        entityManager.createQuery("update Incident set notificationSent = true where id in (:ids)")
                .setParameter("ids", incidentsIds)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void putModels(List<Incident> models) {
        List<Incident> incidents = incidentRepository.findByIncidentIdIn(
                models.stream()
                        .map(Incident::getIncidentId)
                        .collect(Collectors.toList()));
        log.debug("Incidents founded: {}", incidents.size());

        Map<Boolean, List<Incident>> groups = models.stream()
                .collect(Collectors.partitioningBy(incidents::contains));
        log.info("Incidents for insert: {}; for update: {}", groups.get(Boolean.FALSE).size(), groups.get(Boolean.TRUE).size());
        incidentRepository.saveAll(groups.get(Boolean.FALSE));

        incidents.forEach(forUpdate ->
                models.stream()
                        .filter(newData -> forUpdate.getIncidentId().equals(newData.getIncidentId()))
                        .findFirst()
                        .ifPresent(newData -> incidentMapper.updateIncident(newData, forUpdate))
        );
        incidentRepository.saveAll(incidents);
    }

    @Override
    public List<Incident> allByCriteria(List<String> categories,
                                        List<String> affectedSystems,
                                        ZonedDateTime startDate,
                                        String keyword,
                                        Pageable paging) {
        var cb = entityManager.getCriteriaBuilder();
        var criteriaQuery = cb.createQuery(Incident.class);
        var from = criteriaQuery.from(Incident.class);

        List<Predicate> predicates = new ArrayList<>();
        if (affectedSystems != null && !affectedSystems.isEmpty()) {
            Join<Incident, Unavailabilities> join = from.join("unavailabilities");
            predicates.add(join.get("serviceName").in(affectedSystems));
        }
        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(from.get("createdAt"), startDate));
        }
        if (keyword != null && !keyword.isBlank()) {
            predicates.add(cb.like(from.get("incidentId"), "%" + keyword + "%"));
        }

        var categoryPredicate = cb.in(from.get("priority"));
        for (var category : categories) {
            categoryPredicate.value(category);
        }

        predicates.add(categoryPredicate);
        // Только у аварий есть поле identedAt, отфильтровываем инциденты.
        predicates.add(cb.isNotNull(from.get("identedAt")));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(cb.desc(from.get("identedAt")));
        TypedQuery<Incident> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(paging.getPageNumber() * paging.getPageSize());
        query.setMaxResults(paging.getPageSize());
        return query.getResultList();
    }
}
