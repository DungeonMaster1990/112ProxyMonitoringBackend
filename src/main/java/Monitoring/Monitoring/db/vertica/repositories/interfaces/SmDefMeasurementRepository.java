package Monitoring.Monitoring.db.vertica.repositories.interfaces;

import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;

import java.util.List;

public interface SmDefMeasurementRepository {
    List<SmDefMeasurementVertica> getSmDefMeasurements();
}
