package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.AffectedSystem;
import Monitoring.Monitoring.db.models.Incident;
import Monitoring.Monitoring.db.repositories.interfaces.IncidentRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.Clock;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class IncidentRepositoryImpl implements IncidentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Incident> getAllVtbIncidents() {
        Query vtbIncidentsQuery = entityManager.createQuery("select a from Incidents a", Incident.class);
        List<Incident> incidents = vtbIncidentsQuery.getResultList();
        return incidents;
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
    public List<Incident> getTimeFilteredVtbIncidents(long daysDiff){
        Clock cl = Clock.systemUTC();
        ZonedDateTime dt = ZonedDateTime.now(cl).minusDays(daysDiff);

        Query vtbIncidentsQuery = entityManager.createQuery(String.format("select incident from VtbIncidents incident where incident.factBeginAt >= timestamp '%d'", dt), Incident.class);
        List<Incident> incidents = vtbIncidentsQuery.getResultList();
        return incidents;
    }

    @Override
    public void putVtbIncidents(List<Incident> incidents) {
        entityManager.getTransaction().begin();
        for(Incident vtbIncident : incidents){
            entityManager.persist(vtbIncident);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Incident getVtbIncident(int id) {
        String query =  String.format("select a from VtbIncidents a where a.id=%s;", id);
        Incident vtbIncident = entityManager.createQuery(query, Incident.class)
                .getSingleResult();
        return  vtbIncident;
    }

    @Override
    public List<Incident> getVtbIncidents(List<String> incidentIds){

        String idsString = String.join(", ", incidentIds);

        String query = String.format("select a from VtbIncidents a where a.incident_id in %s;", idsString);

        List<Incident> incidents = entityManager.createQuery(query, Incident.class)
                .getResultList();

        return incidents;
    }

    public List<Incident> allByCriteria(List<String> affectedSystems,
                                        ZonedDateTime startDate,
                                        String keyword,
                                        Pageable paging) {
        var cb = entityManager.getCriteriaBuilder();
        var criteriaQuery = cb.createQuery(Incident.class);
        var from = criteriaQuery.from(Incident.class);
        Join<Incident, AffectedSystem> join = from.join("affectedSystems");

        List<Predicate> predicates = new ArrayList<>();
        if (affectedSystems != null && !affectedSystems.isEmpty()) {
            predicates.add(join.get("name").in(affectedSystems));
        }
        if (startDate != null) {
            predicates.add(cb.between(from.get("createdAt"), startDate, startDate.with(LocalTime.MAX)));
        }
        if (keyword != null && !keyword.isBlank()) {
            predicates.add(cb.like(from.get("incidentId"), "%" + keyword + "%"));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Incident> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((paging.getPageNumber()-1) * paging.getPageSize());
        query.setMaxResults(paging.getPageSize());

        return query.getResultList();
    }
}
