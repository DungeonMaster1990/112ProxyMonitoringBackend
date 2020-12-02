package ru.vtb.monitoring.vtb112.workesr;

import org.junit.Assert;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
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
    static void setUpVertica(DynamicPropertyRegistry registry) {
        Vertica.getInstance();
    }

    @BeforeAll
    public void setUp() throws Throwable {
        initialRawDataCount += rawDataMeasApiRepository.findAll().size();
        runSqlScript("vertica_test_data.sql");//13 rows for uploading
    }

    @Test
    @Order(1)
    void testTakeSmDefMeasurementVertica() {
        Assert.assertFalse(metricsRepository.findById(2).map(Metrics::isMerged).orElse(true));
        verticaWorker.takeSmDefMeasurementVertica();
        Assert.assertTrue(metricsRepository.findById(2).map(Metrics::isMerged).orElse(false));
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
        Assert.assertEquals(expectedAfterWorker, updateAfter.getUpdateTime());
        Assert.assertNotEquals(updateBefore.getUpdateTime(), updateAfter.getUpdateTime());
    }

    @Test
    @Order(3)
    void testVerticaNewDataUploading() throws Exception {
        runSqlScript("vertica_new_data.sql"); //12 new rows for uploading
        testTakeSmRawDataMeasVertica();
        Assert.assertEquals(25, rawDataMeasApiRepository.findAll().size()-initialRawDataCount);
    }

    private void runSqlScript(String sqlFileName) throws Exception {
        String file = "src/test/resources/db/vendor/"+sqlFileName;
        String query = Files.readString(Paths.get(file), StandardCharsets.UTF_8);
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        }
    }

}
