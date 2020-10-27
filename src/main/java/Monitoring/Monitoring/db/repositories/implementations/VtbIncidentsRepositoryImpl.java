package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.VtbIncidents;
import Monitoring.Monitoring.db.repositories.interfaces.VtbIncidentsRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class VtbIncidentsRepositoryImpl implements VtbIncidentsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VtbIncidents> getAllVtbIncidents() {
        Query vtbIncidentsQuery = entityManager.createQuery("from monitoring.VtbIncidents", VtbIncidents.class);
        List<VtbIncidents> vtbIncidents = vtbIncidentsQuery.getResultList();
        return vtbIncidents;
    }

    @Override
    public void putVtbIncidents(List<VtbIncidents> vtbIncidents) {
        entityManager.getTransaction().begin();
        for(VtbIncidents vtbIncident : vtbIncidents){
            entityManager.persist(vtbIncident);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public VtbIncidents getVtbIncident(int id) {
        String query =  String.format("from monitoring.VtbIncidents where id=%s;", id);
        VtbIncidents vtbIncident = entityManager.createQuery(query, VtbIncidents.class)
                .getSingleResult();
        return  vtbIncident;
    }
}
