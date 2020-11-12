package Monitoring.Monitoring.db.vertica.repositories.interfaces;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;

import java.util.List;

public interface SmDefMeasurementRepository {
    void putSmDefMeasurements(List<SmDefMeasurementApi> smDefMeasurementApis);
}
