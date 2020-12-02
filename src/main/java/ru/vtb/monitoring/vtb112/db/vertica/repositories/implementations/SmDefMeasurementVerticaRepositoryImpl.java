package ru.vtb.monitoring.vtb112.db.vertica.repositories.implementations;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Metrics;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmDefMeasurementVertica;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmDefMeasurementVerticaRepository;
import ru.vtb.monitoring.vtb112.services.helpers.interfaces.DateFormatterHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SmDefMeasurementVerticaRepositoryImpl implements SmDefMeasurementVerticaRepository {

    private final AppConfig appConfig;
    private final DateFormatterHelper dateFormatterHelper;

    public SmDefMeasurementVerticaRepositoryImpl(AppConfig appConfig, DateFormatterHelper dateFormatterHelper) {
        this.appConfig = appConfig;
        this.dateFormatterHelper = dateFormatterHelper;
    }

    @Override
    public List<SmDefMeasurementVertica> getSmDefMeasurements(List<Metrics> metrics) throws SQLException {
        StringBuilder whereQuery = new StringBuilder();
        for (int i = 0; i < metrics.size(); i++) {
            if (i != metrics.size() - 1) {
                whereQuery.append(String.format("(measurement_id = %s and monitor_id = %s) or", metrics.get(i).getMeasurementId(), metrics.get(i).getMonitorId()));
            } else {
                whereQuery.append(String.format("(measurement_id = %s and monitor_id = %s)", metrics.get(i).getMeasurementId(), metrics.get(i).getMonitorId()));
            }
        }
        List<SmDefMeasurementVertica> smDefMeasurementsVertica = new ArrayList<>();
        String query = String.format("""
                    select session_id, measurement_id, sched_id, category_id, monitor_id, target_id,
                    msname, msid, user_remark, connection_data, dm_connection_id, active, ci_id, eti_id, integration_name,
                    profile_id, creation_date, modified_date, is_deleted 
                    from bsm_replica.SM_DEF_MEASUREMENT
                    where (%s)
                """, whereQuery
        );

        try (Connection connection = DriverManager.getConnection(appConfig.getVerticaUrl(), appConfig.getVerticaUserPass());
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SmDefMeasurementVertica smDefMeasurementVertica = new SmDefMeasurementVertica();
                smDefMeasurementVertica.setSessionId(rs.getInt("session_id"));
                smDefMeasurementVertica.setMeasurementId(rs.getInt("measurement_id"));
                smDefMeasurementVertica.setSchedId(rs.getInt("sched_id"));
                smDefMeasurementVertica.setCategoryId(rs.getInt("category_id"));
                smDefMeasurementVertica.setMonitorId(rs.getInt("monitor_id"));
                smDefMeasurementVertica.setTargetId(rs.getInt("target_id"));
                smDefMeasurementVertica.setMsname(rs.getString("msname"));
                smDefMeasurementVertica.setMsid(rs.getString("msid"));
                smDefMeasurementVertica.setUserRemark(rs.getString("user_remark"));
                smDefMeasurementVertica.setConnectionData(rs.getString("connection_data"));
                smDefMeasurementVertica.setDmConnectionId(rs.getInt("dm_connection_id"));
                smDefMeasurementVertica.setActive(rs.getInt("active"));
                smDefMeasurementVertica.setCiId(rs.getString("ci_id"));
                smDefMeasurementVertica.setEtiId(rs.getString("eti_id"));
                smDefMeasurementVertica.setIntegrationName(rs.getString("integration_name"));
                smDefMeasurementVertica.setProfileId(rs.getString("profile_id"));
                smDefMeasurementVertica.setCreationDate(dateFormatterHelper.dbDateToZonedDate(rs.getTimestamp("creation_date"),"Europe/Moscow"));
                smDefMeasurementVertica.setModifiedDate(dateFormatterHelper.dbDateToZonedDate(rs.getTimestamp("modified_date"), "Europe/Moscow"));
                smDefMeasurementVertica.setIsDeleted(rs.getBoolean("is_deleted"));
                smDefMeasurementsVertica.add(smDefMeasurementVertica);
            }
            return smDefMeasurementsVertica;
        }
    }
}
