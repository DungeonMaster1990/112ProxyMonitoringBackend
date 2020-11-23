package Monitoring.Monitoring.services.api.impl;

import Monitoring.Monitoring.db.repositories.interfaces.MetricsRepository;
import Monitoring.Monitoring.dto.api.viewmodels.enums.BlMetricsStatus;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricInfoRequest;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricInfoResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;
import Monitoring.Monitoring.mappers.MetricsMapper;
import Monitoring.Monitoring.services.api.interfaces.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MetricsServiceImpl implements MetricsService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public VmMetricsResponse[] getMetrics(VmMetricsRequest vmMetricsRequet) {
        // TODO Пока в постановке задачи нет четкого описания как группировать значения measurements
        //  просто берем последнее по времени значение
        String allMetricsQry = """
                select m.id as id,
                       m.msname as name,
                       false as mine,
                       vals.meas_value as value,
                       0 as delta,
                       0 as deltaPercent,
                       0 as deltaStatus,
                       0 as totalPercent
                  from monitoring.metrics m
                  join (select distinct on (measurement_id) meas_value, 
                               measurement_id, 
                               raw_threshold_quality
                          from monitoring.sm_rawdata_meas srm
                         order by measurement_id, time_stamp desc, id desc) vals
                    on m.measurement_id  = vals.measurement_id
                 LIMIT :limit
                OFFSET :offset
                """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("limit", vmMetricsRequet.getLimit())
                .addValue("offset", vmMetricsRequet.getPage() * vmMetricsRequet.getLimit());

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
