package ru.vtb.monitoring.vtb112.db.repositories.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.models.AffectedSystem;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.IncidentRepositoryCustom;
import ru.vtb.monitoring.vtb112.mappers.IncidentMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.Clock;
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

    @Lazy
    private final IncidentRepository incidentRepository;

    private final IncidentMapper incidentMapper;

    public IncidentRepositoryImpl(IncidentRepository incidentRepository, IncidentMapper incidentMapper) {
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
    }

    @Override
    public List<Incident> getAllVtbIncidents() {
        TypedQuery<Incident> vtbIncidentsQuery = entityManager.createQuery("select a from Incidents a", Incident.class);
        return vtbIncidentsQuery.getResultList();
    }

    @Override
    public List<Incident> getTimeFilteredNonSentVtbIncidents(long daysDiff) {

        String qryString = """
                select i 
                  from Incident i 
                 where i.factBeginAt >= :twoDaysBackFromNow
                   and i.notificationSent <> true
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
    public List<Incident> getTimeFilteredVtbIncidents(long daysDiff) {
        Clock cl = Clock.systemUTC();
        ZonedDateTime dt = ZonedDateTime.now(cl).minusDays(daysDiff);
        return entityManager.createQuery(String.format("select incident from VtbIncidents incident where incident.factBeginAt >= timestamp '%d'", dt), Incident.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void putVtbIncidents(List<Incident> incidents) {
        entityManager.getTransaction().begin();
        for (Incident vtbIncident : incidents) {
            entityManager.persist(vtbIncident);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Incident getVtbIncident(int id) {
        String query = String.format("select a from VtbIncidents a where a.id=%s;", id);
        return entityManager.createQuery(query, Incident.class)
                .getSingleResult();
    }

    @Override
    public List<Incident> getVtbIncidents(List<String> incidentIds) {

        String idsString = String.join(", ", incidentIds);

        String query = String.format("select a from VtbIncidents a where a.incident_id in %s;", idsString);

        return entityManager.createQuery(query, Incident.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void putModels(List<Incident> models) {
        List<Incident> incidents = incidentRepository.findByIncidentIdIn(models
                .stream()
                .map(Incident::getIncidentId)
                .collect(Collectors.toList()));
        log.info("incidents founded: {}", incidents.size());

        Map<Boolean, List<Incident>> groups = models
                .stream()
                .collect(Collectors.partitioningBy(incidents::contains));
        log.info("incidents for update: {}", groups.get(Boolean.FALSE).size());
        log.info("incidents for insert: {}", groups.get(Boolean.TRUE).size());
        incidentRepository.saveAll(groups.get(Boolean.FALSE));

        incidents.forEach(forUpdate ->
                models.stream()
                        .filter(newData -> forUpdate.getIncidentId().equals(newData.getIncidentId()))
                        .findFirst()
                        .ifPresent(newData -> incidentMapper.updateIncident(newData, forUpdate))
        );
        incidentRepository.saveAll(incidents);
    }

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
            Join<Incident, AffectedSystem> join = from.join("affectedSystems");
            predicates.add(join.get("name").in(affectedSystems));
        }
        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(from.get("createdAt"), startDate));
        }
        if (keyword != null && !keyword.isBlank()) {
            predicates.add(cb.like(from.get("incidentId"), "%" + keyword + "%"));
        }

        var categoryPredicate = cb.in(from.get("priority"));
        for (var category : categories){
            categoryPredicate.value(category);
        }

        predicates.add(categoryPredicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Incident> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((paging.getPageNumber() - 1) * paging.getPageSize());
        query.setMaxResults(paging.getPageSize());

        return query.getResultList();
    }
}
