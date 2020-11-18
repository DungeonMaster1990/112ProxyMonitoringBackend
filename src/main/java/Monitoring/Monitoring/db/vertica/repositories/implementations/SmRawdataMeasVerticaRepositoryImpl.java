package Monitoring.Monitoring.db.vertica.repositories.implementations;

import Monitoring.Monitoring.db.models.Updates;
import Monitoring.Monitoring.db.vertica.VerticaConnection;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;
import Monitoring.Monitoring.db.vertica.repositories.interfaces.SmRawdataMeasVerticaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class SmRawdataMeasVerticaRepositoryImpl implements SmRawdataMeasVerticaRepository {
    private Connection verticaConnection;

    @Autowired
    public SmRawdataMeasVerticaRepositoryImpl(VerticaConnection verticaConnection) {
        this.verticaConnection = verticaConnection.getConnection();
    }
    @Override
    public List<SmRawdataMeasVertica> getSmRawdataMeasVertica(Updates lastUpdate) throws SQLException {
        List<SmRawdataMeasVertica> smRawdataMeasesVertica = new ArrayList<SmRawdataMeasVertica>();

        Statement stmt = verticaConnection.createStatement();
        String query = String.format("""
                    select session_id, time_stamp, measurement_id, status_id, err_msg, raw_monitor_id, raw_target_id, 
                    raw_connection_id, raw_category_id, raw_threshold_quality, dbdate
                    from bsm_replica.SM_RAWDATA_MEAS
                    where time_stamp > (%s)
                """, lastUpdate.getUpdateTime().toString());
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            SmRawdataMeasVertica smRawdataMeasVertica = new SmRawdataMeasVertica();

            smRawdataMeasVertica.setSessionId(rs.getInt("session_id"));
            smRawdataMeasVertica.setTimeStamp(ZonedDateTime.parse(rs.getString("time_stamp")));
            smRawdataMeasVertica.setMeasurementId(rs.getInt("measurement_id"));
            smRawdataMeasVertica.setMeasValue(rs.getFloat("meas_value"));
            smRawdataMeasVertica.setStatusId(rs.getInt("status_id"));
            smRawdataMeasVertica.setErrMsg(rs.getString("err_msg"));
            smRawdataMeasVertica.setRawMonitorId(rs.getInt("raw_monitor_id"));
            smRawdataMeasVertica.setRawTargetId(rs.getInt("raw_target_id"));
            smRawdataMeasVertica.setRawConnectionId(rs.getInt("raw_connection_id"));
            smRawdataMeasVertica.setRawCategoryId(rs.getInt("raw_category_id"));
            smRawdataMeasVertica.setRawThresholdQuality(rs.getInt("raw_threshold_quality"));
            smRawdataMeasVertica.setDbdate(ZonedDateTime.parse(rs.getString("dbdate")));

            smRawdataMeasesVertica.add(smRawdataMeasVertica);
        }
        return smRawdataMeasesVertica;
    }
}
