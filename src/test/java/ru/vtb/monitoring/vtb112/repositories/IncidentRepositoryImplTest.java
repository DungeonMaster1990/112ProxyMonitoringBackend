package ru.vtb.monitoring.vtb112.repositories;

import com.sun.istack.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
class IncidentRepositoryImplTest extends PostgreSQL {

    @Autowired
    private IncidentRepository incidentRepository;

    @Test
    void testPutModels() {

        incidentRepository.save(makeIncident(1, "Инцидент"));

        Incident oldIncident = makeIncident(1, "Инцидент");
        oldIncident.setDescription("Изменение в описании");
        Incident newIncident = makeIncident(2, "Инцидент");

        incidentRepository.putModels(Arrays.asList(oldIncident, newIncident));

        String description = incidentRepository.findByIncidentIdIn(Collections.singletonList(oldIncident.getIncidentId()))
                .stream()
                .map(Incident::getDescription)
                .findFirst()
                .orElse("");
        newIncident = incidentRepository.findByIncidentIdIn(Collections.singletonList(newIncident.getIncidentId()))
                .stream()
                .findFirst()
                .orElse(null);

        Assert.assertEquals("Изменение в описании", description);
        Assert.assertNotNull(newIncident);
    }

    @NotNull
    private Incident makeIncident(int i, String incidentId) {
        Incident incident = new Incident();
        incident.setIncidentId(incidentId +" I_" + i);
        incident.setSpecialistId("Иванов Василий " + i);
        incident.setDescription("Проблема");
        incident.setCreatedAt(ZonedDateTime.now());
        return incident;
    }
}
