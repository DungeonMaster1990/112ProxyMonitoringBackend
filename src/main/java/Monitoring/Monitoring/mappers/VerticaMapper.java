package Monitoring.Monitoring.mappers;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.models.SmRawdataMeasApi;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;

public interface VerticaMapper {
   SmRawdataMeasApi mapToSmRawdataMeasApi(SmRawdataMeasVertica metrics);
   SmDefMeasurementApi mapToSmDefMeasurementApi(SmDefMeasurementVertica metrics);
}
