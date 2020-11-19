package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.repositories.interfaces.MetricsRepository;
import Monitoring.Monitoring.db.repositories.interfaces.SmDefMeasurementApiRepository;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;
import Monitoring.Monitoring.infrastructure.PostgreSQL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MetricsControllerTest extends PostgreSQL {

    private String metricsResponses = """
            [
                 {
                          "id": "1",
                          "name": "Клиентов в ВТБ Онлайн",
                          "mine": true,
                          "value": "65024",
                          "delta": -56,
                          "deltaPercent": -0.6,
                          "deltaStatus": "warning",
                          "totalPercent": 0.6
                      },
                      {
                          "id": "2",
                          "name": "Очередь на исполнение документов БО",
                          "mine": true,
                          "value": "0",
                          "delta": -5,
                          "deltaPercent": -0.3,
                          "deltaStatus": "normal",
                          "totalPercent": 0.0
                      },
                      {
                          "id": "3",
                          "name": "Количество новых операций в ВТБ-онлайн",
                          "mine": true,
                          "value": "0",
                          "delta": 50,
                          "deltaPercent": 5.3,
                          "deltaStatus": "normal",
                          "totalPercent": -1.0
                      },
                      {
                          "id": "4",
                          "name": "Новых переводов между своими счетами",
                          "mine": true,
                          "value": "0",
                          "delta": 0,
                          "deltaPercent": 0,
                          "deltaStatus": "warning",
                          "totalPercent": 0.0
                      },
                      {
                          "id": "5",
                          "name": "Время формирования выписки",
                          "mine": true,
                          "value": "2.9с",
                          "delta": -5,
                          "deltaPercent": -0.3,
                          "deltaStatus": "normal",
                          "totalPercent": 1.0
                      },
                       {
                              "id": "7",
                              "name": "Процент доставленных PUSH",
                              "mine": true,
                              "value": "97%",
                              "delta": 5,
                              "deltaPercent": 0.6,
                              "deltaStatus": "normal",
                              "totalPercent": 1.0
                          },
                          {
                              "id": "88",
                              "name": "Процент доставленных SMS",
                              "mine": true,
                              "value": "96%",
                              "delta": 5,
                              "deltaPercent": 0.6,
                              "deltaStatus": "normal",
                              "totalPercent": 1.0
                          }
            ]
            """;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MetricsRepository metricsRepository;

    @Autowired
    private SmDefMeasurementApiRepository smDefMeasurementApiRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    Random rand = new Random();

    public static final TypeReference<List<VmMetricsResponse>> METRIC_RESPONSES_TYPE_REF = new TypeReference<>() {
    };

    List<VmMetricsResponse> responses;

    MetricsControllerTest() throws Exception {
        responses = objectMapper.readValue(metricsResponses, METRIC_RESPONSES_TYPE_REF);
    }

    @BeforeAll
    public void setUp() {
        responses.forEach(r -> {
            int monitorId = rand.nextInt();
            metricsRepository.save(makeMetrics(r, monitorId));
            smDefMeasurementApiRepository.save(makeMeas(r, monitorId));
        });
    }

    @Test
    void testAllMetrics() throws Exception {
        VmMetricsRequest request = new VmMetricsRequest(true, "hello", 10, 0);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1.0/metrics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].mine").value(false))
                .andExpect(jsonPath("$[0].delta").value(0))
                .andExpect(jsonPath("$[0].deltaPercent").value(0))
                .andExpect(jsonPath("$[0].totalPercent").value(0))
                .andExpect(jsonPath("$[0].deltaStatus").value("warning"))
                .andExpect(jsonPath("$[0].value").value("65024"))
                .andExpect(jsonPath("$[0].name").value(responses.get(0).getName()))
                .andExpect(jsonPath("$[*])", hasSize(responses.size())));
    }

    @Test
    void testPagedMetrics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1.0/metrics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new VmMetricsRequest(true, "hello", 2, 0))))
                .andExpect(jsonPath("$[*])", hasSize(2)));

        int remainderAfter3Pages = 1;
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1.0/metrics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new VmMetricsRequest(true, "hello", 2, 3))))
                .andExpect(jsonPath("$[*])", hasSize(remainderAfter3Pages)));
    }

    private SmDefMeasurementApi makeMeas(VmMetricsResponse r, int monitorId) {
        return SmDefMeasurementApi
                .builder()
                .measurementId(Integer.valueOf(r.getId()))
                .monitorId(monitorId)
                .sessionId(rand.nextInt())
                .schedId(rand.nextInt())
                .categoryId(rand.nextInt())
                .msname(r.getName())
                .measValue(r.getValue())
                .targetId(rand.nextInt())
                .active(1)
                .rawTresholdQuality(1)
                .modifiedDate(ZonedDateTime.now())
                .creationDate(ZonedDateTime.now())
                .isDeleted(false)
                .build();
    }

    private Metrics makeMetrics(VmMetricsResponse r, int monitorId) {
        return Metrics.builder()
                .measurementId(Integer.valueOf(r.getId()))
                .msname(r.getName())
                .monitorId(monitorId)
                .isMerged(false)
                .build();
    }
}