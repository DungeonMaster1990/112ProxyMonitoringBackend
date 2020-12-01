package ru.vtb.monitoring.vtb112.workesr;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestPropertySource(properties = "vertica.limit=10")
class VerticaWorkerTest extends PostgreSQL {

    @Autowired
    @Qualifier("verticaDataSource")
    DataSource dataSource;
    @Autowired
    private VerticaWorker verticaWorker;
    @Autowired
    private MetricsRepository metricsRepository;
    @Autowired
    private UpdatesRepository updatesRepository;
    @Autowired
    SmRawDataMeasVerticaRepository smRawdataMeasVerticaRepository;

    @DynamicPropertySource
    static void setUpVertica(DynamicPropertyRegistry registry) {
        Vertica.getInstance();
    }

    @BeforeAll
    public void setUp() throws Throwable {
        String query = getTestDataSql();
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()) {
             stmt.execute(query);
        }
    }

    @Test
    void testTakeSmDefMeasurementVertica() {
        Assert.assertFalse(metricsRepository.findById(2).map(Metrics::isMerged).orElse(true));
        verticaWorker.takeSmDefMeasurementVertica();
        Assert.assertTrue(metricsRepository.findById(2).map(Metrics::isMerged).orElse(false));
    }

    @Test
    void testTakeSmRawDataMeasVertica() {
        ZonedDateTime expectedAfterWorker = smRawdataMeasVerticaRepository.findAll().stream()
                .map(SmRawdataMeasVertica::getTimeStamp)
                .max(Comparator.naturalOrder())
                .orElse(null);
        Updates updateBefore = updatesRepository.getUpdateEntityByServiceName("VerticaSmRawData");

        verticaWorker.takeSmRawDataMeasVertica();

        Updates updateAfter = updatesRepository.getUpdateEntityByServiceName("VerticaSmRawData");
        Assert.assertEquals(expectedAfterWorker, updateAfter.getUpdateTime());
        Assert.assertNotEquals(updateBefore.getUpdateTime(), updateAfter.getUpdateTime());
    }

    @NotNull
    private String getTestDataSql() throws Exception {
        String file = "src/test/resources/db/vendor/vertica_test_data.sql";
        return Files.readString(Paths.get(file), StandardCharsets.UTF_8);
    }

}
