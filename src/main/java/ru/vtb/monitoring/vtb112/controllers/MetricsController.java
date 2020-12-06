package ru.vtb.monitoring.vtb112.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.request.VmMetricInfoRequest;
import ru.vtb.monitoring.vtb112.dto.api.request.VmMetricsRequest;
import ru.vtb.monitoring.vtb112.dto.api.response.VmMetricInfoResponse;
import ru.vtb.monitoring.vtb112.dto.api.response.VmMetricsResponse;
import ru.vtb.monitoring.vtb112.dto.api.response.VmUpdateResponse;
import ru.vtb.monitoring.vtb112.services.api.interfaces.MetricsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = PathConstants.METRICS, produces = MediaType.APPLICATION_JSON_VALUE)
public class MetricsController {

    private final MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<VmMetricsResponse> get(@Valid @RequestBody VmMetricsRequest vmMetricsRequest) {
        return metricsService.getMetrics(vmMetricsRequest);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VmUpdateResponse updateMetrics(@RequestBody List<String> vmUpdateRequest) {
        return new VmUpdateResponse(true);
    }

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<VmMetricInfoResponse> getMetricInfo(@Valid @RequestBody VmMetricInfoRequest vmMetricInfoRequest) {
        return metricsService.getMetricsInfos(vmMetricInfoRequest);
    }
}
