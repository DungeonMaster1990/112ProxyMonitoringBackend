package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.Changes;
import Monitoring.Monitoring.db.repositories.interfaces.ChangesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ChangesRepositoryImpl implements ChangesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void putModels(List<Changes> models) {
        entityManager.getTransaction().begin();
        for(Changes vtbChanges : models){
            entityManager.persist(vtbChanges);
        }
        entityManager.getTransaction().commit();
    }
}
