package ru.vtb.monitoring.vtb112.testcases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.controllers.PathConstants;
import ru.vtb.monitoring.vtb112.db.pg.models.Metrics;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.MetricsRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.SmRawdataMeasApiRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.api.request.VmMetricInfoRequest;
import ru.vtb.monitoring.vtb112.dto.api.response.VmMetricInfoResponse;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;
import ru.vtb.monitoring.vtb112.infrastructure.Vertica;
import ru.vtb.monitoring.vtb112.services.workers.VerticaWorker;
import ru.vtb.monitoring.vtb112.services.workers.WorkerName;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(properties = "vertica.limit=10")
class UploadFromVerticaAndFrontendGetTest extends PostgreSQL {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VerticaWorker verticaWorker;
    @Autowired
    private SmRawdataMeasApiRepository smRawdataMeasApiRepository;

    @MockBean
    private UpdatesRepository updatesRepository;
    @MockBean
    private MetricsRepository metricsRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @DynamicPropertySource
    static void setUpVertica(DynamicPropertyRegistry registry) {
        Vertica.getInstance();
    }

    @BeforeAll
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        Updates updates = new Updates();
        updates.setUpdateTime(ZonedDateTime.now());
        Metrics metrics = new Metrics();
        metrics.setMeasurementId(1);
        when(updatesRepository.getUpdateEntityByServiceName(WorkerName.VERTICA_SM_RAW_DATA.getName()))
                .thenReturn(updates);
        when(metricsRepository.findByMerged(true))
                .thenReturn(Collections.singletonList(metrics));
        Vertica.getInstance().runSqlScript("timestamp_test.sql");
    }

    @Test
    void testTimestampCorrectness() throws Exception {
        ZonedDateTime startDate = ZonedDateTime.parse("2120-11-19T07:29:00Z");
        ZonedDateTime endDate = ZonedDateTime.parse("2120-11-19T08:00:00Z");
        verticaWorker.takeSmRawDataMeasVertica();
        smRawdataMeasApiRepository.findAll()
                .forEach(m -> System.out.println(m.getTimeStamp()));
        VmMetricInfoRequest request = VmMetricInfoRequest.builder()
                .startDate(startDate)
                .finishDate(endDate)
                .id("12")
                .build();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.METRICS + "/info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(jsonPath("$[*])", hasSize(2)))
                .andExpect(status().isOk())
                .andReturn();

        List<VmMetricInfoResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, VmMetricInfoResponse.class));

        response.forEach(info -> {
            assertThat(info.getDate(), greaterThan(startDate));
            assertThat(info.getDate(), lessThan(endDate));
        });
    }

    @AfterAll
    public void tearDown() {
        Vertica.getInstance().runSqlScript("drop_data.sql");
    }
}
