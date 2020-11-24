package Monitoring.Monitoring.mappers;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.models.SmRawdataMeasApi;
import Monitoring.Monitoring.db.vertica.models.SmDefMeasurementVertica;
import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VerticaMapper {
   @Mapping(target = "id", ignore = true)
   SmRawdataMeasApi mapToSmRawdataMeasApi(SmRawdataMeasVertica metrics);
   @Mapping(target = "id", ignore = true)
   SmDefMeasurementApi mapToSmDefMeasurementApi(SmDefMeasurementVertica metrics);
}
