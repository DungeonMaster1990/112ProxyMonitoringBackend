package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.SmRawdataMeasApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmRawdataMeasApiRepository {
    void putSmRawdataMeasVertica(List<SmRawdataMeasApi> smRawdataMeasVerticas);
}
