package Monitoring.Monitoring.services.workers.impl;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.models.SmRawdataMeasApi;
import Monitoring.Monitoring.db.repositories.interfaces.SmDefMeasurementApiRepository;
import Monitoring.Monitoring.db.repositories.interfaces.SmRawdataMeasApiRepository;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;
import Monitoring.Monitoring.db.vertica.repositories.interfaces.SmDefMeasurementVerticaRepository;
import Monitoring.Monitoring.db.vertica.repositories.interfaces.SmRawdataMeasVerticaRepository;
import Monitoring.Monitoring.services.workers.interfaces.VerticaWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VerticaWorkerServiceImpl implements VerticaWorkerService {
    private SmDefMeasurementApiRepository smDefMeasurementApiRepository;
    private SmDefMeasurementVerticaRepository smDefMeasurementVerticaRepository;
    private SmRawdataMeasApiRepository smRawdataMeasApiRepository;
    private SmRawdataMeasVerticaRepository smRawdataMeasVerticaRepository;

    @Autowired
    public VerticaWorkerServiceImpl(SmDefMeasurementApiRepository smDefMeasurementApiRepository, SmDefMeasurementVerticaRepository smDefMeasurementVerticaRepository, SmRawdataMeasApiRepository smRawdataMeasApiRepository, SmRawdataMeasVerticaRepository smRawdataMeasVerticaRepository) {
        this.smDefMeasurementApiRepository = smDefMeasurementApiRepository;
        this.smDefMeasurementVerticaRepository = smDefMeasurementVerticaRepository;
        this.smRawdataMeasApiRepository = smRawdataMeasApiRepository;
        this.smRawdataMeasVerticaRepository = smRawdataMeasVerticaRepository;
    }


    @Override
    @Scheduled(fixedRate = 900000)
    public void takeSmDefMeasurementVertica() throws SQLException {
        List<SmDefMeasurementVertica> smDefMeasurementVerticaList = smDefMeasurementVerticaRepository.getSmDefMeasurements();
        List<SmDefMeasurementApi> smDefMeasurementApiList = new ArrayList<SmDefMeasurementApi>();
        smDefMeasurementApiRepository.putSmDefMeasurements(smDefMeasurementApiList);
    }

    @Override
    @Scheduled(fixedRate = 900000)
    public void takeSmRawdataMeasVertica() throws SQLException {
        List<SmRawdataMeasVertica> smRawdataMeasVerticaList = smRawdataMeasVerticaRepository.getSmRawdataMeasVertica(ZonedDateTime.now());
        List<SmRawdataMeasApi> smRawdataMeasApiList = new ArrayList<SmRawdataMeasApi>();
        smRawdataMeasApiRepository.putSmRawdataMeasVertica(smRawdataMeasApiList);
    }
}
