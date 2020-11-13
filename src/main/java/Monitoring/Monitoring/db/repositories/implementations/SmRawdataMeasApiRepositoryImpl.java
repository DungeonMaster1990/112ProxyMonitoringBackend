package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.repositories.interfaces.SmRawdataMeasApiRepository;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SmRawdataMeasApiRepositoryImpl implements SmRawdataMeasApiRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void putSmRawdataMeasVertica(List<SmRawdataMeasVertica> smRawdataMeasVerticas) {
        entityManager.getTransaction().begin();
        for(SmRawdataMeasVertica smRawdataMeasVertica : smRawdataMeasVerticas){
            entityManager.persist(smRawdataMeasVertica);
        }
        entityManager.getTransaction().commit();
    }
}
