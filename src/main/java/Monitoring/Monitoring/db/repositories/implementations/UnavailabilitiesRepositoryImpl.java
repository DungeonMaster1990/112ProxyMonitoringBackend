package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.Unavailabilities;
import Monitoring.Monitoring.db.repositories.interfaces.UnavailabilitiesRepository;
import com.google.common.collect.Streams;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UnavailabilitiesRepositoryImpl implements UnavailabilitiesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Unavailabilities> getAllVtbUnavailabilities() {
        Query vtbUnavailabilityQuery = entityManager.createQuery("select a from VtbUnavailability a", Unavailabilities.class);
        List<Unavailabilities> vtbIncidents = vtbUnavailabilityQuery.getResultList();
        return vtbIncidents;
    }

    @Override
    public void putVtbUnavailabilities(List<Unavailabilities> vtbUnavailabilities) {
        entityManager.getTransaction().begin();
        for(Unavailabilities vtbUnavailability : vtbUnavailabilities){
            entityManager.persist(vtbUnavailability);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Unavailabilities getVtbUnavailability(int id) {
        String query =  String.format("select a from VtbUnavailability a where id=%s;", id);
        Unavailabilities vtbUnavailability = entityManager.createQuery(query, Unavailabilities.class)
                .getSingleResult();
        return vtbUnavailability;
    }

    @Override
    public List<Unavailabilities> getVtbUnavailabilities(String[] faultIds, String[] serviceIds) {

        var requestParam = Streams.zip(Arrays.stream(faultIds), Arrays.stream(serviceIds),
                (faultId, serviceId) -> String.format("(%s, %s)", faultId, serviceId))
                .collect(Collectors.joining(", "));


        String query =  String.format("select unavailabilities from VtbUnavailabilities unavailabilities where (fault_id, service_id) in(%s);", requestParam);
        List<Unavailabilities> vtbUnavailability = entityManager.createQuery(query, Unavailabilities.class)
                .getResultList();
        return vtbUnavailability;
    }
}
