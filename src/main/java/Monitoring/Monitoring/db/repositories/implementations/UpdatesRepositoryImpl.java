package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.Updates;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class UpdatesRepositoryImpl implements UpdatesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Updates getUpdateEntityByServiceName(String serviceName) {
        String qryString = """
                select upd
                  from Updates upd 
                 where upd.serviceName = :serviceName
                """;
        TypedQuery<Updates> vtbIncidentsQuery = entityManager.createQuery(qryString, Updates.class)
                .setParameter("serviceName", serviceName);
        Updates update = vtbIncidentsQuery.getSingleResult();

        return update;
    }

    @Override
    public void putUpdate(Updates update){
        entityManager.merge(update);
    }
}
