package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.db.repositories.interfaces.MetricsRepository;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.infrastructure.PostgreSQL;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
class MetricsControllerTest extends PostgreSQL {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MetricsRepository metricsRepository;

    @Autowired
    DataSource dataSource;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSaveMetrics() throws Exception {
        List<Metrics> allMetrics = IntStream.rangeClosed(1, 10)
                                            .mapToObj(this::makeMetrics)
                                            .collect(Collectors.toList());
        metricsRepository.saveAll(allMetrics);
        VmMetricsRequest request = new VmMetricsRequest(true, "hello", 10, 0);
        mockMvc.perform(MockMvcRequestBuilders
                                .post("/api/v1.0/metrics")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("\"id\":\"1\"")));
    }

    @NotNull
    private Metrics makeMetrics(int i) {
        Metrics metrics = new Metrics();
        metrics.setMeasurementId(i);
        metrics.setMsname(UUID.randomUUID().toString());
        return metrics;
    }
}