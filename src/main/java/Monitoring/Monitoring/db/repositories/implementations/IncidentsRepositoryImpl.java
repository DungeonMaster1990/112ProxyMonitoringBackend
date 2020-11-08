package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.Incidents;
import Monitoring.Monitoring.db.repositories.interfaces.IncidentsRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class IncidentsRepositoryImpl implements IncidentsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Incidents> getAllVtbIncidents() {
        Query vtbIncidentsQuery = entityManager.createQuery("select a from VtbIncidents a", Incidents.class);
        List<Incidents> incidents = vtbIncidentsQuery.getResultList();
        return incidents;
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
    public void putVtbIncidents(List<Incidents> incidents) {
        entityManager.getTransaction().begin();
        for(Incidents vtbIncident : incidents){
            entityManager.persist(vtbIncident);
        }
        entityManager.getTransaction().commit();
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
}
