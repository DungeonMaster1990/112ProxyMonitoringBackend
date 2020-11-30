package ru.vtb.monitoring.vtb112.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmMetricInfoRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmMetricsRequest;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
class MetricsControllerTest extends PostgreSQL {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    MetricsControllerTest() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testAllMetrics() throws Exception {
        VmMetricsRequest request = new VmMetricsRequest(true, "hello", 10, 1);
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.METRICS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(jsonPath("$[*])", hasSize(3)))
                .andExpect(jsonPath("$[0]['value'])").value(String.format("%,d", 1000000L)))
                .andExpect(jsonPath("$[1]['value'])").value(String.format("%,d", 1000000L)))
                .andExpect(jsonPath("$[2]['value'])").value(String.format("%,d", 1000000L)))
                .andExpect(status().isOk());
    }

    @Test
    void testPagedMetrics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.METRICS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new VmMetricsRequest(true, "hello", 2, 1))))
                .andExpect(jsonPath("$[*])", hasSize(2)));

        int remainderAfter3Pages = 2;
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.METRICS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new VmMetricsRequest(true, "hello", 2, 1))))
                .andExpect(jsonPath("$[*])", hasSize(remainderAfter3Pages)));
    }

    @Test
    void testMetricsInfos() throws Exception {
        Integer metricsId = jdbcTemplate.queryForObject("select id from monitoring.metrics where msname = 'Выданные КК'", Integer.class);
        assertNotNull(metricsId);
        VmMetricInfoRequest req = VmMetricInfoRequest.builder()
                .id(metricsId.toString())
                .startDate(ZonedDateTime.now().minus(4, ChronoUnit.DAYS))
                .finishDate(ZonedDateTime.now())
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.METRICS + "/info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(jsonPath("$[*])", hasSize(4)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testMetricsInfosOneDay() throws Exception {
        Integer metricsId = jdbcTemplate.queryForObject("select id from monitoring.metrics where msname = 'Выданные КК'", Integer.class);
        assertNotNull(metricsId);
        VmMetricInfoRequest req = VmMetricInfoRequest.builder()
                .id(metricsId.toString())
                .startDate(ZonedDateTime.now().minus(1, ChronoUnit.DAYS).minus(5, ChronoUnit.MINUTES))
                .finishDate(ZonedDateTime.now())
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.METRICS + "/info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(jsonPath("$[*])", hasSize(2)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}