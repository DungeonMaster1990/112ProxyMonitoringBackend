package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlMetricsStatus;
import ru.vtb.monitoring.vtb112.dto.api.request.VmMetricInfoRequest;
import ru.vtb.monitoring.vtb112.dto.api.request.VmMetricsRequest;
import ru.vtb.monitoring.vtb112.dto.api.response.VmMetricInfoResponse;
import ru.vtb.monitoring.vtb112.dto.api.response.VmMetricsResponse;
import ru.vtb.monitoring.vtb112.services.api.interfaces.MetricsService;

import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class MetricsServiceImpl implements MetricsService {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MetricsServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<VmMetricsResponse> getMetrics(VmMetricsRequest request) {
        // TODO Пока в постановке задачи нет четкого описания как группировать значения measurements
        // так что просто берем последнее по времени значение
        String allMetricsQry = """
                select m.id as id,
                       m.msname as name,
                       vals.meas_value as value
                  from monitoring.metrics m
                  join (select distinct on (measurement_id) meas_value, 
                               measurement_id, 
                               raw_threshold_quality
                          from monitoring.sm_rawdata_meas srm
                         order by measurement_id, time_stamp desc, id desc) vals
                    on m.measurement_id  = vals.measurement_id
                   where lower(m.msname) like :keyword
                 order by m.id
                 LIMIT :limit
                OFFSET :offset
                """;

        String keyword = "%" + (request.getKeyword() == null ? "" : request.getKeyword().toLowerCase()) + "%";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("limit", request.getLimit())
                .addValue("offset", (request.getPage() - 1) * request.getLimit())
                .addValue("keyword", keyword);

        return namedParameterJdbcTemplate.query(
                allMetricsQry, sqlParameterSource, (rs, rowNum) -> toMetricsResponse(rs));
    }

    @Override
    public List<VmMetricInfoResponse> getMetricsInfos(@Valid VmMetricInfoRequest vmMetricInfoRequest) {
        String metricInfosQry = """
                select id, time_stamp , measurement_id , meas_value , raw_threshold_quality
                  from monitoring.sm_rawdata_meas srm
                 where measurement_id = (
                	select measurement_id
                	  from monitoring.metrics m
                	 where m.id = :id)
                   and srm.time_stamp between :startDate and :finishDate
                order by srm.time_stamp
                """;

        var params = new MapSqlParameterSource()
                .addValue("id", Integer.valueOf(vmMetricInfoRequest.getId()))
                .addValue("startDate", vmMetricInfoRequest.getStartDate().toOffsetDateTime())
                .addValue("finishDate", vmMetricInfoRequest.getFinishDate().toOffsetDateTime());

        return namedParameterJdbcTemplate.query(
                metricInfosQry, params, (rs, rowNum) -> toMetricInfoResponse(rs));
    }

    private VmMetricInfoResponse toMetricInfoResponse(ResultSet rs) throws SQLException {
        return VmMetricInfoResponse.builder()
                .date(rs.getTimestamp("time_stamp").toInstant().atOffset(ZoneOffset.UTC).toZonedDateTime())
                .deltaStatus(BlMetricsStatus.resolve(rs.getInt("raw_threshold_quality")))
                .deltaPercent(0)
                .delta(0)
                .value(rs.getLong("meas_value"))
                .build();
    }

    private VmMetricsResponse toMetricsResponse(ResultSet rs) throws SQLException {
        return VmMetricsResponse
                .builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .value(String.format("%,d", rs.getLong("value")))
                .deltaStatus(BlMetricsStatus.normal)
                .build();
    }
}
