package Monitoring.Monitoring.services.api.impl;

import Monitoring.Monitoring.db.repositories.interfaces.MetricsRepository;
import Monitoring.Monitoring.dto.api.viewmodels.enums.BlMetricsStatus;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;
import Monitoring.Monitoring.mappers.MetricsMapper;
import Monitoring.Monitoring.services.api.interfaces.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MetricsServiceImpl implements MetricsService {

    @Autowired
    private MetricsRepository metricsRepository;

    @Autowired
    private MetricsMapper metricsMapper;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public VmMetricsResponse[] getMetrics(VmMetricsRequest vmMetricsRequet, VmMetricsRequest request) {
        String allMetricsQry = """
                   select m.id as id,
                          m.msname as name,
                          false as mine,
                          sdm.meas_value as value,
                          0 as delta,
                          0 as deltaPercent,
                          sdm.raw_threshold_quality as deltaStatus,
                          0 as totalPercent
                    from monitoring.metrics m
                inner join monitoring.sm_def_measurements sdm
                   on m.measurement_id  = sdm.measurement_id
                  and m.monitor_id = sdm.monitor_id
                  order by m.id
                  LIMIT :limit
                  OFFSET :offset
                """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("limit", request.getLimit())
                .addValue("offset", request.getPage() * request.getLimit());

        List<VmMetricsResponse> result = namedParameterJdbcTemplate.query(
                allMetricsQry, sqlParameterSource, (rs, rowNum) -> resultSetToResponse(rs));

        return result.toArray(new VmMetricsResponse[0]);
    }

    private VmMetricsResponse resultSetToResponse(ResultSet rs) throws SQLException {
        return VmMetricsResponse
                .builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .mine(rs.getBoolean("mine"))
                .value(rs.getString("value"))
                .delta(rs.getLong("delta"))
                .deltaPercent(rs.getDouble("deltaPercent"))
                .deltaStatus(BlMetricsStatus.resolve(rs.getInt("deltaStatus")))
                .totalPercent(0)
                .build();
    }
}
