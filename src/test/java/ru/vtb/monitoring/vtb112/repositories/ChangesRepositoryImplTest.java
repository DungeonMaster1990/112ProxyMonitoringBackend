package ru.vtb.monitoring.vtb112.repositories;

import com.sun.istack.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.db.pg.models.Changes;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.ChangesRepository;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
class ChangesRepositoryImplTest extends PostgreSQL {

    @Autowired
    private ChangesRepository changesRepository;

    @Test
    void testPutModels() {
        changesRepository.save(makeChange(1, "Изменение"));
        Changes oldChange = makeChange(1, "Инцидент");
        oldChange.setDescription("Изменение в описании");
        Changes newChange = makeChange(2, "Инцидент");
        changesRepository.putModels(Arrays.asList(oldChange, newChange));

        String description = changesRepository.findByChangeIdIn(Collections.singletonList(oldChange.getChangeId()))
                .stream()
                .map(Changes::getDescription)
                .findFirst()
                .orElse("");
        newChange = changesRepository.findByChangeIdIn(Collections.singletonList(newChange.getChangeId()))
                .stream()
                .findFirst()
                .orElse(null);

        Assertions.assertEquals("Изменение в описании", description);
        Assertions.assertNotNull(newChange);
    }

    @Test
    void testGetGroupedChanges() {

    }

    @NotNull
    private Changes makeChange(int i, String changeId) {
        Changes changes = new Changes();
        changes.setChangeId(changeId + " S_" + i);
        changes.setDescription("Описание");
        return changes;
    }
}
