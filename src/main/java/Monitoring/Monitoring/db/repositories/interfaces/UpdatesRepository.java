package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Updates;

public interface UpdatesRepository {

    Updates getUpdateEntityByServiceName(String serviceName);
    void putUpdate(Updates update);
}
