package Monitoring.Monitoring.mappers;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse.VmMetricsResponseBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-14T00:55:21+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class MetricsMapperImpl implements MetricsMapper {

    @Override
    public VmMetricsResponse mapToMetricsResponse(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        VmMetricsResponseBuilder vmMetricsResponse = VmMetricsResponse.builder();

        if ( metrics.getId() != null ) {
            vmMetricsResponse.id( String.valueOf( metrics.getId() ) );
        }

        return vmMetricsResponse.build();
    }
}
