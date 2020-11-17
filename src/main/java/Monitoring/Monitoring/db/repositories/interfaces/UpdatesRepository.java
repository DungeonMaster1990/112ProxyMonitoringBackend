package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Updates;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdatesRepository {

    Updates getUpdateEntityByServiceName(String serviceName);
    void putUpdate(Updates update);
}
