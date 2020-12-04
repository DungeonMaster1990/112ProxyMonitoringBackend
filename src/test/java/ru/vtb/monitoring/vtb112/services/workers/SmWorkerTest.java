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

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    @Value("classpath:json/sm/incidents.json")
    private Resource incidents;

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
        mockServer = MockRestServiceServer.createServer(smRestTemplate);
    }

    @AfterEach
    void tearDown() {
        mockServer.reset();
    }

    @Test
    void smIncident() {
        baseSmCall(WorkerName.SM_INCIDENTS, incidents, false);
    }

    @Test
    void smChanges() {
        baseSmCall(WorkerName.SM_CHANGES, changes, false);
    }

    @Test
    void smUnavailability() {
        baseSmCall(WorkerName.SM_UNAVAILABILITIES, unavailability, false);
    }

    @Test
    void smIncidentEmpty() {
        baseSmCall(WorkerName.SM_INCIDENTS, incidentsEmpty, true);
    }

    @Test
    void smChangesEmpty() {
        baseSmCall(WorkerName.SM_CHANGES, changesEmpty, true);
    }

    @Test
    void smUnavailabilityEmpty() {
        baseSmCall(WorkerName.SM_UNAVAILABILITIES, unavailabilityEmpty, true);
    }

    private void baseSmCall(WorkerName workerName, Resource response, boolean isEmpty) {
        String url = getUrl(workerName);
        var urlWithParams = BaseSmWorker.buildUrl(appConfig.getSmPort(), url);
        var update = updatesRepository.getUpdateEntityByServiceName(workerName.getName());
        var updateTime = update.getUpdateTime().toInstant();
        mockServer.expect(once(), requestToUriTemplate(urlWithParams, "expand", BaseSmWorker.buildQuery(updateTime)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(asString(response)));
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
            verify(repo).putModels(anyList());
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