package ru.vtb.monitoring.vtb112.workesr;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.SmRawdataMeasApiRepository;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmRawDataMeasVerticaRepository;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;
import ru.vtb.monitoring.vtb112.infrastructure.Vertica;
import ru.vtb.monitoring.vtb112.services.workers.VerticaWorker;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "vertica.limit=2")
public class WorkerUnitTest extends PostgreSQL {

    @MockBean
    private SmRawDataMeasVerticaRepository repository;

    @Autowired
    private VerticaWorker verticaWorker;

    @Autowired
    private SmRawdataMeasApiRepository smRawdataMeasApiRepository;

    @DynamicPropertySource
    static void setUpVertica(DynamicPropertyRegistry registry) {
        Vertica.getInstance();
    }

    @Test
    void testRollbackAfterException() {

        int beforeTestCount = smRawdataMeasApiRepository.findAll().size();

        when(repository.findByStatusIdAndTimeStampGreaterThanAndMeasurementIdIn(any(), any(), any(), any()))
                .thenReturn(Arrays.asList(makeRawData(1), makeRawData(2)))
                .thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {verticaWorker.takeSmRawDataMeasVertica();});

        int afterTestCount = smRawdataMeasApiRepository.findAll().size();
        Assertions.assertEquals(beforeTestCount, afterTestCount);
    }

    private SmRawdataMeasVertica makeRawData(Integer sessionId) {

        SmRawdataMeasVertica rawDataMeasVertica = new SmRawdataMeasVertica();

        rawDataMeasVertica.setSessionId(sessionId);
        rawDataMeasVertica.setTimeStamp(ZonedDateTime.now());
        rawDataMeasVertica.setMeasurementId(1);
        rawDataMeasVertica.setStatusId(0);
        rawDataMeasVertica.setMeasValue(0F);
        rawDataMeasVertica.setRawMonitorId(0);
        rawDataMeasVertica.setRawTargetId(0);
        rawDataMeasVertica.setRawCategoryId(0);
        rawDataMeasVertica.setRawCategoryId(0);
        rawDataMeasVertica.setRawConnectionId(0);

        return rawDataMeasVertica;
    }
}
