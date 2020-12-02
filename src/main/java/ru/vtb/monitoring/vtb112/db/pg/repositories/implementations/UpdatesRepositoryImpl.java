package ru.vtb.monitoring.vtb112.db.pg.repositories.implementations;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;

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

        return vtbIncidentsQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void putUpdate(Updates update) {
        entityManager.merge(update);
    }
}
