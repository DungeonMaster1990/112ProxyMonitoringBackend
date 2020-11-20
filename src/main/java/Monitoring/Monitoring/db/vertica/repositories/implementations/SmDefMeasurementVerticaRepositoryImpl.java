package Monitoring.Monitoring.db.vertica.repositories.implementations;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.db.vertica.VerticaConnection;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;
import Monitoring.Monitoring.db.vertica.repositories.interfaces.SmDefMeasurementVerticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class SmDefMeasurementVerticaRepositoryImpl implements SmDefMeasurementVerticaRepository {
    private Connection verticaConnection;

    @Autowired
    public SmDefMeasurementVerticaRepositoryImpl(VerticaConnection verticaConnection) {
        this.verticaConnection = verticaConnection.getConnection();
    }

    @Override
    public List<SmDefMeasurementVertica> getSmDefMeasurements(List<Metrics> metrics) throws SQLException {
        ArrayList metricsArr = new ArrayList(metrics);
        StringBuilder whereQuery = new StringBuilder();
        for(int i=0; i < metricsArr.size(); i++){
            if(i != metricsArr.size()-1)
                whereQuery.append(String.format("(measurement_id = %o and monitor_id = %o) or", metrics.get(i).getMeasurementId(), metrics.get(i).getMonitorId()));
            else
                whereQuery.append(String.format("(measurement_id = %o and monitor_id = %o)", metrics.get(i).getMeasurementId(), metrics.get(i).getMonitorId()));
        }
        List<SmDefMeasurementVertica> smDefMeasurementsVertica = new ArrayList<SmDefMeasurementVertica>();
        Statement stmt = verticaConnection.createStatement();
        String query = String.format("""
                    select session_id, measurement_id, shed_id, category_id, monitor_id, target_id,
                    msname, msid, user_remark, connection_data, dm_connection_id, active, ci_id, eti_id, integration_name,
                    profile_id, creation_date, modified_date, deleted 
                    from bsm_replica.SM_DEF_MEASUREMENT
                    where (%s)
                """, whereQuery
        );

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            SmDefMeasurementVertica smDefMeasurementVertica = new SmDefMeasurementVertica();
            smDefMeasurementVertica.setSessionId(rs.getInt("session_id"));
            smDefMeasurementVertica.setMeasurementId(rs.getInt("measurement_id"));
            smDefMeasurementVertica.setSchedId(rs.getInt("shed_id"));
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
            smDefMeasurementVertica.setCreationDate(ZonedDateTime.parse(rs.getString("creation_date")));
            smDefMeasurementVertica.setModifiedDate(ZonedDateTime.parse(rs.getString("modified_date")));
            smDefMeasurementVertica.setDeleted(rs.getBoolean("deleted"));
            smDefMeasurementsVertica.add(smDefMeasurementVertica);
        }
        return smDefMeasurementsVertica;
    }
}
