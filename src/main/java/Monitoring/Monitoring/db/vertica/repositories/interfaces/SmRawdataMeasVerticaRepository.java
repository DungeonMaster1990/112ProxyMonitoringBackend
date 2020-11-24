package Monitoring.Monitoring.db.vertica.repositories.interfaces;

import Monitoring.Monitoring.db.models.Updates;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface SmRawdataMeasVerticaRepository {
    List<SmRawdataMeasVertica> getSmRawdataMeasVertica(Updates lastUpdate) throws SQLException;
}
