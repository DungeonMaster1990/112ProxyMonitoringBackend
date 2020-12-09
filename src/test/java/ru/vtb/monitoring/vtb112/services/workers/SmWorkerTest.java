package ru.vtb.monitoring.vtb112.services.workers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.*;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {"sm.max-pages=10", "sm.limit-per-request=10"})
class SmWorkerTest extends PostgreSQL {

    @Autowired
    @Qualifier("smRestTemplate")
    private RestTemplate smRestTemplate;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private SmIncidentsWorker smIncidentsWorker;

    @Autowired
    private SmChangesWorker smChangesWorker;

    @Autowired
    private SmUnavailabilityWorker smUnavailabilityWorker;

    @MockBean
    private IncidentRepository incidentRepository;

    @MockBean
    private ChangesRepository changesRepository;

    @MockBean
    private UnavailabilitiesRepository unavailabilitiesRepository;

    @Autowired
    private UpdatesRepository updatesRepository;

    @Value("classpath:json/sm/incidents_page_1.json")
    private Resource incidents_page_1;
    @Value("classpath:json/sm/incidents_page_2.json")
    private Resource incidents_page_2;

    @Value("classpath:json/sm/changes.json")
    private Resource changes;

    @Value("classpath:json/sm/unavailability.json")
    private Resource unavailability;

    @Value("classpath:json/sm/incidents-empty.json")
    private Resource incidentsEmpty;

    @Value("classpath:json/sm/changes-empty.json")
    private Resource changesEmpty;

    @Value("classpath:json/sm/unavailability-empty.json")
    private Resource unavailabilityEmpty;

    @Value("${sm.port:}")
    private Integer smPort;
    @Value("${sm.limit-per-request}")
    private Integer limitPerRequest;

    private MockRestServiceServer mockServer;

    private static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @BeforeAll
    void setUp() {
        mockServer = MockRestServiceServer.bindTo(smRestTemplate).ignoreExpectOrder(true).build();
    }

    @AfterEach
    void tearDown() {
        mockServer.reset();
    }

    @Test
    void smIncident() {
        baseSmCall(WorkerName.SM_INCIDENTS, Arrays.asList(incidents_page_1, incidents_page_2), false);
    }

    @Test
    void smChanges() {
        baseSmCall(WorkerName.SM_CHANGES, Collections.singletonList(changes), false);
    }

    @Test
    void smUnavailability() {
        baseSmCall(WorkerName.SM_UNAVAILABILITIES, Collections.singletonList(unavailability), false);
    }

    @Test
    void smIncidentEmpty() {
        baseSmCall(WorkerName.SM_INCIDENTS, Collections.singletonList(incidentsEmpty), true);
    }

    @Test
    void smChangesEmpty() {
        baseSmCall(WorkerName.SM_CHANGES, Collections.singletonList(changesEmpty), true);
    }

    @Test
    void smUnavailabilityEmpty() {
        baseSmCall(WorkerName.SM_UNAVAILABILITIES, Collections.singletonList(unavailabilityEmpty), true);
    }

    private void baseSmCall(WorkerName workerName, List<Resource> pages, boolean isEmpty) {
        String url = getUrl(workerName);

        var update = updatesRepository.getUpdateEntityByServiceName(workerName.getName());
        var updateTime = update.getUpdateTime().toInstant();

        for (Resource page: pages) {
            int pageIndex = pages.indexOf(page) + 1;
            var urlWithParams = SmUtils.buildUrl(url, smPort, updateTime, pageIndex, limitPerRequest);
            Object[] uriVars = new Object[]{"expand", SmUtils.buildQuery(updateTime), pageIndex, limitPerRequest};
            mockServer.expect(once(), requestToUriTemplate(urlWithParams, uriVars))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(asString(page)));
        }
        var worker = getWorker(workerName);
        worker.run();

        mockServer.verify();
        var repo = getRepository(workerName);
        var newUpdate = updatesRepository.getUpdateEntityByServiceName(workerName.getName());
        if (isEmpty) {
            assertEquals(update.getUpdateTime(), newUpdate.getUpdateTime());
            verify(repo, never()).putModels(anyList());
        } else {
            assertNotEquals(newUpdate.getUpdateTime(), update.getUpdateTime());
            verify(repo, times(pages.size())).putModels(anyList());
        }
    }

    private String getUrl(WorkerName workerName) {
        return switch (workerName) {
            case SM_CHANGES -> appConfig.getSmChangesUrl();
            case SM_INCIDENTS -> appConfig.getSmIncidentUrl();
            case SM_UNAVAILABILITIES -> appConfig.getSmUnavailabilityUrl();
            case VERTICA_SM_RAW_DATA -> null;
        };
    }

    private Runnable getWorker(WorkerName workerName) {
        return switch (workerName) {
            case SM_CHANGES -> smChangesWorker::loadChangesFromSm;
            case SM_INCIDENTS -> smIncidentsWorker::loadIncidentsFromSm;
            case SM_UNAVAILABILITIES -> smUnavailabilityWorker::loadUnavailabilitiesFromSm;
            case VERTICA_SM_RAW_DATA -> null;
        };
    }

    private SmRepository<?> getRepository(WorkerName workerName) {
        return switch (workerName) {
            case SM_CHANGES -> changesRepository;
            case SM_INCIDENTS -> incidentRepository;
            case SM_UNAVAILABILITIES -> unavailabilitiesRepository;
            case VERTICA_SM_RAW_DATA -> null;
        };
    }
}