package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;

@Repository
public interface UpdatesRepository {

    Updates getUpdateEntityByServiceName(String serviceName);
    void putUpdate(Updates update);
}
