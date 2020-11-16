package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.repositories.interfaces.SmDefMeasurementApiRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SmDefMeasurementApiRepositoryImpl implements SmDefMeasurementApiRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void putSmDefMeasurements(List<SmDefMeasurementApi> smDefMeasurementApis){
        entityManager.getTransaction().begin();
        for(SmDefMeasurementApi smDefMeasurementApi : smDefMeasurementApis){
            entityManager.persist(smDefMeasurementApi);
        }
        entityManager.getTransaction().commit();
    }
}
