package ru.vtb.monitoring.vtb112.repositories;

import com.sun.istack.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
class UnavailabilitiesRepositoryImplTest extends PostgreSQL {

    @Autowired
    private UnavailabilitiesRepository unavailabilitiesRepo;

    @Test
    void testPutModels() {
        unavailabilitiesRepo.save(makeUnavailability(1, "Сервер", "Банк"));

        Unavailabilities oldUnavailability = makeUnavailability(1, "Сервер", "Банк");
        oldUnavailability.setType("Новый тип");
        Unavailabilities newUnavailability = makeUnavailability(2, "Сервер", "Банк");

        unavailabilitiesRepo.putModels(Arrays.asList(oldUnavailability, newUnavailability));

        String type = unavailabilitiesRepo.findByFaultIdInAndServiceIdIn(
                Collections.singletonList(oldUnavailability.getFaultId()),
                Collections.singletonList(oldUnavailability.getServiceId()))
                .stream()
                .map(Unavailabilities::getType)
                .findFirst()
                .orElse("");
        newUnavailability = unavailabilitiesRepo.findByFaultIdInAndServiceIdIn(
                Collections.singletonList(newUnavailability.getFaultId()),
                Collections.singletonList(newUnavailability.getServiceId()))
                .stream()
                .findFirst()
                .orElse(null);

        Assertions.assertEquals("Новый тип", type);
        Assertions.assertNotNull(newUnavailability);
    }

    @NotNull
    private Unavailabilities makeUnavailability(int i, String faultId, String serviceId) {
        Unavailabilities unavailability = new Unavailabilities();
        unavailability.setServiceId(serviceId + " S_" + i);
        unavailability.setFaultId(faultId + " F_" + i);
        unavailability.setCreatedAt(ZonedDateTime.now());
        unavailability.setType("Тип");
        unavailability.setBeginAt(ZonedDateTime.now());
        unavailability.setCreatedById("A_1");
        unavailability.setDuration(Duration.ofSeconds(15));
        unavailability.setServiceName("Service");
        unavailability.setEndAt(ZonedDateTime.now());
        unavailability.setUpdatedAt(ZonedDateTime.now());
        unavailability.setUpdatedById(100);
        return unavailability;
    }
}
