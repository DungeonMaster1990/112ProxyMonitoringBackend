package ru.vtb.monitoring.vtb112.db.vertica.repositories.implementations;

import liquibase.pro.packaged.S;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Metrics;
import ru.vtb.monitoring.vtb112.db.models.Updates;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmRawdataMeasVerticaRepository;
import ru.vtb.monitoring.vtb112.services.helpers.interfaces.DateFormatterHelper;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SmRawdataMeasVerticaRepositoryImpl implements SmRawdataMeasVerticaRepository {

    private final DateFormatterHelper dateFormatterHelper;
    private final AppConfig appConfig;

    public SmRawdataMeasVerticaRepositoryImpl(DateFormatterHelper dateFormatterHelper, AppConfig appConfig) {
        this.dateFormatterHelper = dateFormatterHelper;
        this.appConfig = appConfig;
    }

    @Override
    public List<SmRawdataMeasVertica> getSmRawdataMeasVertica(Updates lastUpdate, List<Metrics> metrics ) throws SQLException {
        List<SmRawdataMeasVertica> smRawdataMeasesVertica = new ArrayList<>();

        StringBuilder metricsIn = new StringBuilder();
        for (int i = 0; i < metrics.size(); i++) {
            if (i != metrics.size() - 1) {
                metricsIn.append(String.format("%s, ", metrics.get(i).getMeasurementId()));
            } else {
                metricsIn.append(String.format("%s", metrics.get(i).getMeasurementId()));
            }
        }
        String query = String.format("""
                    select session_id, time_stamp, measurement_id, status_id, err_msg, raw_monitor_id, raw_target_id, 
                    raw_connection_id, raw_category_id, raw_threshold_quality, dbdate, meas_value
                    from bsm_replica.SM_RAWDATA_MEAS
                    where status_id = 0 and time_stamp > ? and measurement_id in (%s)
                    limit ?
                    offset ?
                """, metricsIn.toString());

        var formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try (Connection connection = DriverManager.getConnection(appConfig.getVerticaUrl(), appConfig.getVerticaUserPass());
             PreparedStatement stmt = connection.prepareStatement(query)) {
            var timestamp = Timestamp.from(lastUpdate.getUpdateTime().toInstant());
            int offset = 0;
            int cur = 0;
            while (cur++ < appConfig.getVerticaMaxPages()) {
                stmt.setTimestamp(1, timestamp);
                stmt.setInt(2, appConfig.getVerticaLimit());
                stmt.setInt(3, offset);
                try (ResultSet rs = stmt.executeQuery()) {
                    offset = offset + appConfig.getVerticaLimit();
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
            }
            return smRawdataMeasesVertica;
        }
    }
}
