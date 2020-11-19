package Monitoring.Monitoring.db.repositories.implementations;

import Monitoring.Monitoring.db.models.Changes;
import Monitoring.Monitoring.db.repositories.interfaces.ChangesRepository;
import Monitoring.Monitoring.db.repositories.interfaces.ChangesRepositoryCustom;
import Monitoring.Monitoring.mappers.ChangesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ChangesRepositoryImpl implements ChangesRepositoryCustom {

    @Autowired
    @Lazy
    ChangesRepository changesRepository;

    @Autowired
    ChangesMapper changesMapper;

    @Override
    @Transactional
    public void putModels(List<Changes> models) {
        List<Changes> changes = changesRepository.findByChangeIdIn(models
                .stream()
                .map(Changes::getChangeId)
                .collect(Collectors.toList()));
        log.info("incidents founded: {}", changes.size());

        Map<Boolean, List<Changes>> groups = models
                .stream()
                .collect(Collectors.partitioningBy(changes::contains));
        log.info("incidents for update: {}", groups.get(Boolean.FALSE).size());
        log.info("incidents for insert: {}", groups.get(Boolean.TRUE).size());
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
