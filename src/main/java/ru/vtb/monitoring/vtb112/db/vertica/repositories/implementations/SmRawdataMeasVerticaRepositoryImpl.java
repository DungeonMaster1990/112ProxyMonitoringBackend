package ru.vtb.monitoring.vtb112.db.vertica.repositories.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Updates;
import ru.vtb.monitoring.vtb112.db.vertica.VerticaConnection;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmRawdataMeasVerticaRepository;
import ru.vtb.monitoring.vtb112.services.helpers.interfaces.DateFormatterHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SmRawdataMeasVerticaRepositoryImpl implements SmRawdataMeasVerticaRepository {
    private final VerticaConnection verticaConnection;
    private final DateFormatterHelper dateFormatterHelper;

    @Autowired
    public SmRawdataMeasVerticaRepositoryImpl(VerticaConnection verticaConnection, DateFormatterHelper dateFormatterHelper) {
        this.verticaConnection = verticaConnection;
        this.dateFormatterHelper = dateFormatterHelper;
    }

    @Override
    public List<SmRawdataMeasVertica> getSmRawdataMeasVertica(Updates lastUpdate) throws SQLException {
        List<SmRawdataMeasVertica> smRawdataMeasesVertica = new ArrayList<>();

        Statement stmt = verticaConnection.getConnection().createStatement();
        Timestamp timestamp = Timestamp.from(lastUpdate.getUpdateTime().toInstant());
        Integer offset = 0;
        String query = """
                    select session_id, time_stamp, measurement_id, status_id, err_msg, raw_monitor_id, raw_target_id, 
                    raw_connection_id, raw_category_id, raw_threshold_quality, dbdate, meas_value
                    from bsm_replica.SM_RAWDATA_MEAS
                    where status_id = 0 and time_stamp > '%s'
                    limit 100
                    offset %s
                """;
        while (true) {
            String newQuery = String.format(query, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(timestamp), Integer.toString(offset));
            ResultSet rs = stmt.executeQuery(newQuery);
            offset = offset + 100;
            if (!rs.next()) {
                break;
            } else {
                do {
                    SmRawdataMeasVertica smRawdataMeasVertica = new SmRawdataMeasVertica();

                    smRawdataMeasVertica.setSessionId(rs.getInt("session_id"));
                    smRawdataMeasVertica.setTimeStamp(dateFormatterHelper.dbDateToZonedDate(rs.getTimestamp("time_stamp")));
                    smRawdataMeasVertica.setMeasurementId(rs.getInt("measurement_id"));
                    smRawdataMeasVertica.setMeasValue(rs.getFloat("meas_value"));
                    smRawdataMeasVertica.setStatusId(rs.getInt("status_id"));
                    smRawdataMeasVertica.setErrMsg(rs.getString("err_msg"));
                    smRawdataMeasVertica.setRawMonitorId(rs.getInt("raw_monitor_id"));
                    smRawdataMeasVertica.setRawTargetId(rs.getInt("raw_target_id"));
                    smRawdataMeasVertica.setRawConnectionId(rs.getInt("raw_connection_id"));
                    smRawdataMeasVertica.setRawCategoryId(rs.getInt("raw_category_id"));
                    smRawdataMeasVertica.setRawThresholdQuality(rs.getInt("raw_threshold_quality"));
                    smRawdataMeasVertica.setDbdate(dateFormatterHelper.dbDateToZonedDate(rs.getTimestamp("dbdate")));

                    smRawdataMeasesVertica.add(smRawdataMeasVertica);
                } while (rs.next());
            }
        }
        return smRawdataMeasesVertica;
    }
}
