package Monitoring.Monitoring.mappers;

import Monitoring.Monitoring.db.models.Incident;
import Monitoring.Monitoring.db.models.Unavailabilities;
import Monitoring.Monitoring.db.repositories.interfaces.IncidentRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UnavailabilitiesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import Monitoring.Monitoring.infrastructure.PostgreSQL;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
public class MappersTest extends PostgreSQL {

    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    UnavailabilitiesRepository unavailabilitiesRepository;

    @Autowired
    IncidentMapper incidentMapper;

    @Autowired
    UnavailabilityMapper unavailabilityMapper;

    @Test
    public void testIncidentMapper() {
        VmSmIncident incident = VmSmIncident.builder()
                .id("Авария")
                .description(new String[]{"1", "2" ,"3"})
                .build();
        Incident savedIncident = incidentRepository.save(incidentMapper.mapToIncidentResponse(incident));
        Assert.assertEquals("Авария", savedIncident.getIncidentId());
        Assert.assertEquals(3, savedIncident.getDescription().split(System.lineSeparator()).length);
    }

    @Test
    public void testUnavailabilityMapper() {
        VmSmUnavailability unavailability = VmSmUnavailability.builder()
                .faultId("1")
                .beginAt(ZonedDateTime.now())
                .createdAt(ZonedDateTime.now())
                .createdById("A_1")
                .duration("15")
                .serviceName("Service")
                .serviceId("5")
                .faultId("1")
                .endAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .updatedById(100)
                .type("Type")
                .build();
        Unavailabilities savedIncident = unavailabilitiesRepository.save(
                unavailabilityMapper.mapToIncidentResponse(unavailability));
        Assert.assertEquals("1", savedIncident.getFaultId());
        Assert.assertEquals(Integer.valueOf(15), savedIncident.getDuration());
    }

}
