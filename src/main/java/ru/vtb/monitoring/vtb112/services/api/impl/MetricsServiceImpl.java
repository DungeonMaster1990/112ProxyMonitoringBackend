package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.BlMetricsStatus;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmMetricInfoRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmMetricsRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmMetricInfoResponse;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmMetricsResponse;
import ru.vtb.monitoring.vtb112.services.api.interfaces.MetricsService;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MetricsServiceImpl implements MetricsService {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MetricsServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public VmMetricsResponse[] getMetrics(VmMetricsRequest vmMetricsRequet) {
        // TODO Пока в постановке задачи нет четкого описания как группировать значения measurements
        // так что просто берем последнее по времени значение
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
                 order by m.id
                 LIMIT :limit
                OFFSET :offset
                """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("limit", vmMetricsRequet.getLimit())
                .addValue("offset", (vmMetricsRequet.getPage() - 1) * vmMetricsRequet.getLimit());

        List<VmMetricsResponse> result = namedParameterJdbcTemplate.query(
                allMetricsQry, sqlParameterSource, (rs, rowNum) -> toMetricsResponse(rs));

        return result.toArray(new VmMetricsResponse[0]);
    }

    @Override
    public VmMetricInfoResponse[] getMetricsInfos(VmMetricInfoRequest vmMetricInfoRequest) {
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

        List<VmMetricInfoResponse> result = namedParameterJdbcTemplate.query(
                metricInfosQry, params, (rs, rowNum) -> toMetricInfoResponse(rs));

        return result.toArray(new VmMetricInfoResponse[0]);
    }

    private Date toDate(ZonedDateTime startDate) {
        return new Date(java.util.Date.from(startDate.toInstant()).getTime());
    }

    private VmMetricInfoResponse toMetricInfoResponse(ResultSet rs) throws SQLException {
        return VmMetricInfoResponse.builder()
                .date(rs.getObject("time_stamp", OffsetDateTime.class).toZonedDateTime())
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
                .mine(rs.getBoolean("mine"))
                .value(String.format("%,d", rs.getLong("value")))
                .delta(rs.getLong("delta"))
                .deltaPercent(rs.getDouble("deltaPercent"))
                .deltaStatus(BlMetricsStatus.resolve(rs.getInt("deltaStatus")))
                .totalPercent(0)
                .build();
    }
}
