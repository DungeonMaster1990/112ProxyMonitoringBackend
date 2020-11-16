package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.SmRawdataMeasApi;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;

import java.util.List;

public interface SmRawdataMeasApiRepository {
    void putSmRawdataMeasVertica(List<SmRawdataMeasApi> smRawdataMeasVerticas);
}
