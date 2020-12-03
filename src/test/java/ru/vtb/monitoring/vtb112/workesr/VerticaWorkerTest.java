package ru.vtb.monitoring.vtb112.workesr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.db.pg.models.Metrics;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.MetricsRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.SmRawdataMeasApiRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;
import ru.vtb.monitoring.vtb112.infrastructure.Vertica;
import ru.vtb.monitoring.vtb112.services.workers.VerticaWorker;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmRawDataMeasVerticaRepository;

import javax.sql.DataSource;
import java.time.ZonedDateTime;
import java.util.Comparator;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(properties = "vertica.limit=10")
class VerticaWorkerTest extends PostgreSQL {

    private static final String SERVICE_NAME = "VerticaSmRawData";

    @Autowired
    @Qualifier("verticaDataSource")
    private DataSource dataSource;
    @Autowired
    private VerticaWorker verticaWorker;
    @Autowired
    private MetricsRepository metricsRepository;
    @Autowired
    private UpdatesRepository updatesRepository;
    @Autowired
    private SmRawDataMeasVerticaRepository smRawdataMeasVerticaRepository;
    @Autowired
    private SmRawdataMeasApiRepository rawDataMeasApiRepository;

    private int initialRawDataCount;

    @DynamicPropertySource
    static void setUpVertica(DynamicPropertyRegistry registry) { Vertica.getInstance(); }

    @BeforeAll
    public void setUp() {
        initialRawDataCount += rawDataMeasApiRepository.findAll().size();
    }

    @Test
    @Order(1)
    void testTakeSmDefMeasurementVertica() {
        Assertions.assertFalse(metricsRepository.findById(2).map(Metrics::isMerged).orElse(true));
        verticaWorker.takeSmDefMeasurementVertica();
        Assertions.assertTrue(metricsRepository.findById(2).map(Metrics::isMerged).orElse(false));
    }

    @Test
    @Order(2)
    void testTakeSmRawDataMeasVertica() {
        ZonedDateTime expectedAfterWorker = smRawdataMeasVerticaRepository.findAll().stream()
                .map(SmRawdataMeasVertica::getTimeStamp)
                .max(Comparator.naturalOrder())
                .orElse(null);
        Updates updateBefore = updatesRepository.getUpdateEntityByServiceName(SERVICE_NAME);

        verticaWorker.takeSmRawDataMeasVertica();

        Updates updateAfter = updatesRepository.getUpdateEntityByServiceName(SERVICE_NAME);
        Assertions.assertEquals(expectedAfterWorker, updateAfter.getUpdateTime());
        Assertions.assertNotEquals(updateBefore.getUpdateTime(), updateAfter.getUpdateTime());
    }

    @Test
    @Order(3)
    void testVerticaNewDataUploading() {
        Vertica.getInstance().runSqlScript("vertica_new_data.sql"); //12 new rows for uploading
        testTakeSmRawDataMeasVertica();
        Assertions.assertEquals(25, rawDataMeasApiRepository.findAll().size()-initialRawDataCount);
    }
}
