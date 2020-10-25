package Monitoring.Monitoring.db.repositories;

import Monitoring.Monitoring.db.models.Accident;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccidentsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Accident> getAccidents(){
        return entityManager.createQuery("from Accidents a order by id", Accident.class)
                .getResultList();
    }
}
