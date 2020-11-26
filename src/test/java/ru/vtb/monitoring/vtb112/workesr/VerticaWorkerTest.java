package ru.vtb.monitoring.vtb112.workesr;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.db.models.Metrics;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.MetricsRepository;
import ru.vtb.monitoring.vtb112.db.vertica.VerticaConnection;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;
import ru.vtb.monitoring.vtb112.infrastructure.Vertica;
import ru.vtb.monitoring.vtb112.services.workers.VerticaWorker;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Statement;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VerticaWorkerTest extends PostgreSQL {

    @Autowired
    VerticaWorker verticaWorker;

    @Autowired
    VerticaConnection verticaConnection;

    @Autowired
    MetricsRepository metricsRepository;

    @DynamicPropertySource
    static void setUpVerticaProperties(DynamicPropertyRegistry registry) {
        Vertica.setVerticaProperties(registry);
    }

    @BeforeAll
    public void setUp() throws Throwable {
        Statement stmt = verticaConnection.getConnection().createStatement();
        String query = getTestDataSql();
        stmt.execute(query);
    }

    @Test
    public void testTakeSmDefMeasurementVertica() {
        Assert.assertFalse(metricsRepository.findById(2).map(Metrics::isMerged).orElse(true));
        verticaWorker.takeSmDefMeasurementVertica();
        Assert.assertTrue(metricsRepository.findById(2).map(Metrics::isMerged).orElse(false));
    }

    @Test
    public void testTakeSmRawDataMeasVertica() {
        verticaWorker.takeSmRawdataMeasVertica();
    }

    @NotNull
    private String getTestDataSql() throws Exception {
        String file = "src/test/resources/db/vendor/vertica_test_data.sql";
        return Files.readString(Paths.get(file), StandardCharsets.UTF_8);
    }

}
