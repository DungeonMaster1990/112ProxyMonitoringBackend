package Monitoring.Monitoring.db.repositories.implementations;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Monitoring.Monitoring.db.models.Incidents;
import Monitoring.Monitoring.db.repositories.interfaces.IncidentsRepository;

@Component
public class IncidentsRepositoryImpl implements IncidentsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Incidents> getAllVtbIncidents() {
        Query vtbIncidentsQuery = entityManager.createQuery("select a from Incidents a", Incidents.class);
        List<Incidents> incidents = vtbIncidentsQuery.getResultList();
        return incidents;
    }

    @Override
    public List<Incidents> getTimeFilteredNonSentVtbIncidents(long daysDiff) {

        String qryString = """
                select i 
                  from Incidents i 
                 where i.factBeginAt >= :twoDaysBackFromNow
                   and i.notificationSent <> true
                """;

        TypedQuery<Incidents> vtbIncidentsQuery = entityManager
                .createQuery(qryString, Incidents.class)
                .setParameter("twoDaysBackFromNow", ZonedDateTime.now().minusDays(daysDiff));

        return vtbIncidentsQuery.getResultList();
    }

    @Override
    @Transactional
    public void markAsNotificationSent(Set<Integer> incidentsIds) {
        entityManager.createQuery("update Incidents set notificationSent = true where id in (:ids)")
                     .setParameter("ids", incidentsIds)
                     .executeUpdate();
    }

    @Override
    public List<Incidents> getTimeFilteredVtbIncidents(long daysDiff){
        Clock cl = Clock.systemUTC();
        ZonedDateTime dt = ZonedDateTime.now(cl).minusDays(daysDiff);

        Query vtbIncidentsQuery = entityManager.createQuery(String.format("select incident from VtbIncidents incident where incident.factBeginAt >= timestamp '%d'", dt), Incidents.class);
        List<Incidents> incidents = vtbIncidentsQuery.getResultList();
        return incidents;
    }

    @Override
    public Incidents getVtbIncident(int id) {
        String query =  String.format("select a from VtbIncidents a where a.id=%s;", id);
        Incidents vtbIncident = entityManager.createQuery(query, Incidents.class)
                .getSingleResult();
        return  vtbIncident;
    }

    @Override
    public List<Incidents> getVtbIncidents(List<String> incidentIds){

        String idsString = String.join(", ", incidentIds);

        String query = String.format("select a from VtbIncidents a where a.incident_id in %s;", idsString);

        List<Incidents> incidents = entityManager.createQuery(query, Incidents.class)
                .getResultList();

        return incidents;
    }

    @Override
    public void putModels(List<Incidents> models) {
        entityManager.getTransaction().begin();
        for(Incidents vtbIncident : models){
            entityManager.persist(vtbIncident);
        }
        entityManager.getTransaction().commit();
    }
}
