package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vtb.monitoring.vtb112.db.pg.models.SmDefMeasurementApi;
import ru.vtb.monitoring.vtb112.db.pg.models.SmRawdataMeasApi;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmDefMeasurementVertica;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;

@Mapper(componentModel = "spring")
public interface VerticaMapper {
   @Mapping(target = "id", ignore = true)
   SmRawdataMeasApi mapToSmRawdataMeasApi(SmRawdataMeasVertica metrics);
   @Mapping(target = "id", ignore = true)
   SmDefMeasurementApi mapToSmDefMeasurementApi(SmDefMeasurementVertica metrics);
}
