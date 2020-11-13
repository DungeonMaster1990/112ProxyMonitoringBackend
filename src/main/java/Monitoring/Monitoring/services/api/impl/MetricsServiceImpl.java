package Monitoring.Monitoring.services.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.db.repositories.interfaces.MetricsRepository;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;
import Monitoring.Monitoring.services.api.interfaces.MetricsService;

@Service
public class MetricsServiceImpl implements MetricsService {

    @Autowired
    MetricsRepository metricsRepository;

    @Override
    public VmMetricsResponse[] getMetrics(VmMetricsRequest vmMetricsRequest) {
        List<Metrics> result = metricsRepository.findAll();
        return new VmMetricsResponse[0];
    }
}
