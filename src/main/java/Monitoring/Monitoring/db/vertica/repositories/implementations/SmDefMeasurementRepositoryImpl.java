package Monitoring.Monitoring.db.vertica.repositories.implementations;

import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;
import Monitoring.Monitoring.db.vertica.repositories.interfaces.SmDefMeasurementRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SmDefMeasurementRepositoryImpl implements SmDefMeasurementRepository {

    @Override
    public SmDefMeasurementVertica getDefMeasurements() {
        return null;
    }
}
