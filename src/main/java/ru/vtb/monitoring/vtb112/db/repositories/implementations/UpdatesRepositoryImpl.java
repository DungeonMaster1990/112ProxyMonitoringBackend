package ru.vtb.monitoring.vtb112.db.repositories.implementations;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Updates;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
    @Transactional
    public void putUpdate(Updates update){
        entityManager.merge(update);
    }
}
