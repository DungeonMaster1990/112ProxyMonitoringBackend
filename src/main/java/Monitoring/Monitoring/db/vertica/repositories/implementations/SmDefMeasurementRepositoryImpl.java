package Monitoring.Monitoring.db.vertica.repositories.implementations;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;
import Monitoring.Monitoring.db.vertica.repositories.interfaces.SmDefMeasurementRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SmDefMeasurementRepositoryImpl implements SmDefMeasurementRepository {

    @Override
    public void putSmDefMeasurements(List<SmDefMeasurementApi> smDefMeasurementApis) {

    }
}
