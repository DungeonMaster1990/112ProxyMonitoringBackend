package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.Accidents;
import Monitoring.Monitoring.db.models.VtbIncidents;
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

    public List<Accidents> getAllAccidents() {
        Query accidentsQuery = entityManager.createQuery("select a from Accidents a", Accidents.class);
        List<Accidents> accidentsList = accidentsQuery.getResultList();
        return accidentsList;
    }

    @Override
    public void putAccidents(List<Accidents> vtbAccidents) {
        entityManager.getTransaction().begin();
        for(Accidents vtbAccident : vtbAccidents){
            entityManager.persist(vtbAccident);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Accidents getVtbAccident(int id) {
        String query = String.format("select a from accidents a where id=%s;", id);
        Accidents vtbAccident = entityManager.createQuery(query, Accidents.class)
                .getSingleResult();
        return vtbAccident;
    }
}
