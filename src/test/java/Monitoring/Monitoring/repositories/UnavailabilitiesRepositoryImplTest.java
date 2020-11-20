package Monitoring.Monitoring.repositories;

import Monitoring.Monitoring.db.models.Unavailabilities;
import Monitoring.Monitoring.db.repositories.interfaces.UnavailabilitiesRepository;
import Monitoring.Monitoring.infrastructure.PostgreSQL;
import com.sun.istack.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
public class UnavailabilitiesRepositoryImplTest extends PostgreSQL {

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

        Assert.assertEquals("Новый тип", type);
        Assert.assertNotNull(newUnavailability);

    }

    @NotNull
    private Unavailabilities makeUnavailability(int i, String fault_id, String service_id) {

        Unavailabilities unavailability = new Unavailabilities();
        unavailability.setServiceId(service_id +" S_" + i);
        unavailability.setFaultId(fault_id +" F_" + i);

        unavailability.setCreatedAt(ZonedDateTime.now());
        unavailability.setType("Тип");
        unavailability.setBeginAt(ZonedDateTime.now());
        unavailability.setCreatedById("A_1");
        unavailability.setDuration(15);
        unavailability.setServiceName("Service");
        unavailability.setEndAt(ZonedDateTime.now());
        unavailability.setUpdatedAt(ZonedDateTime.now());
        unavailability.setUpdatedById(100);

        return unavailability;
    }

}
