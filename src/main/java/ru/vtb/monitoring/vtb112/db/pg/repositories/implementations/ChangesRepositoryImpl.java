package ru.vtb.monitoring.vtb112.db.pg.repositories.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.pg.models.Changes;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.ChangesRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.ChangesRepositoryCustom;
import ru.vtb.monitoring.vtb112.mappers.ChangesMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ChangesRepositoryImpl implements ChangesRepositoryCustom {

    @Autowired
    @Lazy
    private ChangesRepository changesRepository;

    @Autowired
    private ChangesMapper changesMapper;

    @Override
    @Transactional
    public void putModels(List<Changes> models) {
        List<Changes> changes = changesRepository.findByChangeIdIn(models
                .stream()
                .map(Changes::getChangeId)
                .collect(Collectors.toList()));
        log.debug("Changes founded: {}", changes.size());

        Map<Boolean, List<Changes>> groups = models
                .stream()
                .collect(Collectors.partitioningBy(changes::contains));
        log.info("Changes for insert: {}; for update: {}", groups.get(Boolean.FALSE).size(), groups.get(Boolean.TRUE).size());
        changesRepository.saveAll(groups.get(Boolean.FALSE));

        changes.forEach(forUpdate ->
                models.stream()
                        .filter(newData -> forUpdate.getChangeId().equals(newData.getChangeId()))
                        .findFirst()
                        .ifPresent(newData -> changesMapper.updateChange(newData, forUpdate))
        );
        changesRepository.saveAll(changes);
    }
}
