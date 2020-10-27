package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.VtbIncidents;
import Monitoring.Monitoring.db.models.VtbUnavailability;
import Monitoring.Monitoring.db.repositories.interfaces.VtbUnavailabilityRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class VtbUnavailabilityRepositoryImpl implements VtbUnavailabilityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VtbUnavailability> getAllVtbUnavailabilities() {
        Query vtbUnavailabilityQuery = entityManager.createQuery("from monitoring.VtbUnavailability", VtbUnavailability.class);
        List<VtbUnavailability> vtbIncidents = vtbUnavailabilityQuery.getResultList();
        return vtbIncidents;
    }

    @Override
    public void putVtbUnavailabilities(List<VtbUnavailability> vtbUnavailabilities) {
        entityManager.getTransaction().begin();
        for(VtbUnavailability vtbUnavailability : vtbUnavailabilities){
            entityManager.persist(vtbUnavailability);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public VtbUnavailability getVtbUnavailability(int id) {
        String query =  String.format("from monitoring.VtbUnavailability where id=%s;", id);
        VtbUnavailability vtbUnavailability = entityManager.createQuery(query, VtbUnavailability.class)
                .getSingleResult();
        return vtbUnavailability;
    }
}
