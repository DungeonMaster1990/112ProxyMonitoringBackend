package ru.vtb.monitoring.vtb112.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vtb.monitoring.vtb112.db.models.Changes;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.ChangesRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmChange;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.submodels.VmSmChangeHeader;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MappersTest extends PostgreSQL {

    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private UnavailabilitiesRepository unavailabilitiesRepository;
    @Autowired
    private ChangesRepository changesRepository;
    @Autowired
    private IncidentMapper incidentMapper;
    @Autowired
    private UnavailabilityMapper unavailabilityMapper;
    @Autowired
    private ChangesMapper changesMapper;

    @Test
    void testIncidentMapper() {
        VmSmIncident incident = VmSmIncident.builder()
                .id("Авария")
                .description(new String[]{"1", "2", "3"})
                .build();
        Incident savedIncident = incidentRepository.save(incidentMapper.mapToIncidentResponse(incident));
        assertEquals("Авария", savedIncident.getIncidentId());
        assertEquals(3, savedIncident.getDescription().split(System.lineSeparator()).length);
    }

    @Test
    void testUnavailabilityMapper() {
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
        assertEquals("1", savedIncident.getFaultId());
        assertEquals(Integer.valueOf(15), savedIncident.getDuration());
    }

    @Test
    void testChangesMapper() {
        VmSmChange change = new VmSmChange();
        VmSmChangeHeader header = new VmSmChangeHeader();
        header.setId("ID_1");
        change.setHeader(header);

        Changes savedIncident = changesRepository.save(
                changesMapper.mapToChangesResponse(change));
        assertEquals("ID_1", savedIncident.getChangeId());
    }

}
