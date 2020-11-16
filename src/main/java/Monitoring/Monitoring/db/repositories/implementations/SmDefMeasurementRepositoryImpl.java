package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.vertica.repositories.interfaces.SmDefMeasurementRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SmDefMeasurementRepositoryImpl implements SmDefMeasurementRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void putSmDefMeasurements(List<SmDefMeasurementApi> smDefMeasurementApis){
        entityManager.getTransaction().begin();
        for(SmDefMeasurementApi smDefMeasurementApi : smDefMeasurementApis){
            entityManager.persist(smDefMeasurementApi);
        }
        entityManager.getTransaction().commit();
    }
}
