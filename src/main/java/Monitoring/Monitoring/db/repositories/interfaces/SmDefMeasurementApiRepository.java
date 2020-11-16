package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;

import java.util.List;

public interface SmDefMeasurementApiRepository {
    void putSmDefMeasurements(List<SmDefMeasurementApi> smDefMeasurementApis);
}
