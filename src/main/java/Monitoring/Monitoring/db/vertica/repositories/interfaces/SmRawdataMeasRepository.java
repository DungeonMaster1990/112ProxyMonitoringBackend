package Monitoring.Monitoring.db.vertica.repositories.interfaces;

import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;

import java.util.List;

public interface SmRawdataMeasRepository {
    void putSmRawdataMeasVertica(List<SmRawdataMeasVertica> smRawdataMeasVerticas);
}
