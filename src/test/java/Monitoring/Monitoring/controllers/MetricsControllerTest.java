package Monitoring.Monitoring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.db.repositories.interfaces.MetricsRepository;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
class MetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MetricsRepository metricsRepository;

    @Autowired
    DataSource dataSource;

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("iapp")
            .withUsername("iapp")
            .withPassword("iapp");


    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSaveMetrics() throws Exception {
        Metrics metrics = new Metrics();
        metrics.setMeasurementId(0);
        metrics.setMsname("some-ms-name");
        metricsRepository.saveAndFlush(metrics);

        VmMetricsRequest request = new VmMetricsRequest(true, "hello", 10, 0);
        mockMvc.perform(MockMvcRequestBuilders
                                .post("/api/v1.0/metrics")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("\"id\":\"1\"")));
    }
}