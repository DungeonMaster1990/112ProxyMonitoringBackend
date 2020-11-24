package ru.vtb.monitoring.vtb112.db.repositories.interfaces;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Updates;

@Repository
public interface UpdatesRepository {

    Updates getUpdateEntityByServiceName(String serviceName);
    void putUpdate(Updates update);
}
