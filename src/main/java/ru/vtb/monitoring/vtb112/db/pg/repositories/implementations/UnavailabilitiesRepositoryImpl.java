package ru.vtb.monitoring.vtb112.db.pg.repositories.implementations;

import com.google.common.collect.Streams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UnavailabilitiesRepositoryCustom;
import ru.vtb.monitoring.vtb112.mappers.UnavailabilityMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class UnavailabilitiesRepositoryImpl implements UnavailabilitiesRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private UnavailabilitiesRepository availabilitiesRepo;

    @Autowired
    private UnavailabilityMapper unavailabilityMapper;

    @Override
    public List<Unavailabilities> getAllVtbUnavailabilities() {
        return entityManager.createQuery("select a from VtbUnavailability a", Unavailabilities.class).getResultList();
    }

    @Override
    public Unavailabilities getVtbUnavailability(int id) {
        String query = String.format("select a from VtbUnavailability a where id=%s;", id);
        return entityManager.createQuery(query, Unavailabilities.class)
                .getSingleResult();
    }

    @Override
    public List<Unavailabilities> getVtbUnavailabilities(String[] faultIds, String[] serviceIds) {
        var requestParam = Streams.zip(Arrays.stream(faultIds), Arrays.stream(serviceIds),
                (faultId, serviceId) -> String.format("(%s, %s)", faultId, serviceId))
                .collect(Collectors.joining(", "));


        String query = String.format("select unavailabilities from VtbUnavailabilities unavailabilities where (fault_id, service_id) in(%s);", requestParam);
        return entityManager.createQuery(query, Unavailabilities.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void putModels(List<Unavailabilities> models) {
        List<Unavailabilities> unavailabilities = availabilitiesRepo.findByFaultIdInAndServiceIdIn(
                models.stream()
                        .map(Unavailabilities::getFaultId)
                        .collect(Collectors.toList()),
                models.stream()
                        .map(Unavailabilities::getServiceId)
                        .collect(Collectors.toList()));
        log.info("Unavailabilities founded: {}", unavailabilities.size());

        Map<Boolean, List<Unavailabilities>> groups = models.stream()
                .collect(Collectors.partitioningBy(unavailabilities::contains));
        log.info("Unavailabilities for insert: {}; for update: {}", groups.get(Boolean.FALSE).size(), groups.get(Boolean.TRUE).size());

        availabilitiesRepo.saveAll(groups.get(Boolean.FALSE));

        unavailabilities.forEach(forUpdate ->
                models.stream()
                        .filter(newData -> forUpdate.getServiceId().equals(newData.getServiceId())
                                && forUpdate.getFaultId().equals(newData.getFaultId()))
                        .findFirst()
                        .ifPresent(newData -> unavailabilityMapper.updateUnavailability(newData, forUpdate))
        );
        availabilitiesRepo.saveAll(unavailabilities);
    }
}
