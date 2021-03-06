package ru.vtb.monitoring.vtb112.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vtb.monitoring.vtb112.db.pg.models.Changes;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.ChangesRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmChange;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmIncident;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmUnavailability;
import ru.vtb.monitoring.vtb112.dto.sm.response.submodels.VmSmChangeHeader;
import ru.vtb.monitoring.vtb112.dto.sm.response.submodels.VmSmChangeMiddle;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import java.time.Duration;
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
        Incident savedIncident = incidentRepository.save(incidentMapper.mapToResponse(incident));
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
                .duration("PT0H0M15S")
                .serviceName("Service")
                .serviceId("5")
                .faultId("1")
                .endAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .updatedById("Иванов И.И.")
                .type("Type")
                .build();
        Unavailabilities savedUnavailabilities = unavailabilitiesRepository.save(
                unavailabilityMapper.mapToResponse(unavailability));
        assertEquals("1", savedUnavailabilities.getFaultId());
        assertEquals(Duration.ofSeconds(15), savedUnavailabilities.getDuration());
    }

    @Test
    void testChangesMapper() {
        ZonedDateTime headerStart = ZonedDateTime.now().minusDays(3);
        ZonedDateTime headerEnd = ZonedDateTime.now().plusDays(3);
        ZonedDateTime middleStart = ZonedDateTime.now().minusDays(1);
        ZonedDateTime middleEnd = ZonedDateTime.now().plusDays(1);

        VmSmChange change = new VmSmChange();
        VmSmChangeHeader header = new VmSmChangeHeader();
        header.setId("ID_1");
        header.setPlannedStartAt(headerStart);
        header.setPlannedEndAt(headerEnd);

        change.setHeader(header);

        VmSmChangeMiddle middle = new VmSmChangeMiddle();
        middle.setSchedOutageStartAt(middleStart);
        middle.setSchedOutageEndAt(middleEnd);

        change.setMiddle(middle);

        Changes savedChanges = changesRepository.save(changesMapper.mapToResponse(change));
        assertEquals("ID_1", savedChanges.getChangeId());
        assertEquals(headerStart, savedChanges.getPlannedStartAt());
        assertEquals(headerEnd, savedChanges.getPlannedEndAt());
        assertEquals(middleStart, savedChanges.getSchedOutageStartAt());
        assertEquals(middleEnd, savedChanges.getSchedOutageEndAt());
    }

}
