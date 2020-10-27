package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.Accidents;
import Monitoring.Monitoring.db.repositories.interfaces.AccidentsRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class AccidentsRepositoryImpl implements AccidentsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Accidents> getAccidents(){

        Query accidentsQuery = entityManager.createQuery("select a from Accidents a", Accidents.class);
        System.out.println(accidentsQuery.toString());
        List<Accidents> accidentsList = accidentsQuery.getResultList();

        return accidentsList;
    }
}
