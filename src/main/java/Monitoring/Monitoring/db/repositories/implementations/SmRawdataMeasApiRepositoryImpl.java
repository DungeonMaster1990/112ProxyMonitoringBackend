package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.SmRawdataMeasApi;
import Monitoring.Monitoring.db.repositories.interfaces.SmRawdataMeasApiRepository;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SmRawdataMeasApiRepositoryImpl implements SmRawdataMeasApiRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void putSmRawdataMeasVertica(List<SmRawdataMeasApi> smRawdataMeasesApi) {
        entityManager.getTransaction().begin();
        for(SmRawdataMeasApi smRawdataMeasApi : smRawdataMeasesApi){
            entityManager.persist(smRawdataMeasApi);
        }
        entityManager.getTransaction().commit();
    }
}
