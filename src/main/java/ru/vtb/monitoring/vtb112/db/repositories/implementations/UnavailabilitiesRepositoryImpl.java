package ru.vtb.monitoring.vtb112.db.repositories.implementations;

import com.google.common.collect.Streams;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UnavailabilitiesRepositoryCustom;
import ru.vtb.monitoring.vtb112.mappers.UnavailabilityMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Repository
public class UnavailabilitiesRepositoryImpl implements UnavailabilitiesRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    UnavailabilitiesRepository availabilitiesRepo;

    @Autowired
    UnavailabilityMapper unavailabilityMapper;

    @Override
    public List<Unavailabilities> getAllVtbUnavailabilities() {
        Query vtbUnavailabilityQuery = entityManager.createQuery("select a from VtbUnavailability a", Unavailabilities.class);
        List<Unavailabilities> vtbIncidents = vtbUnavailabilityQuery.getResultList();
        return vtbIncidents;
    }

    @Override
    public Unavailabilities getVtbUnavailability(int id) {
        String query =  String.format("select a from VtbUnavailability a where id=%s;", id);
        Unavailabilities vtbUnavailability = entityManager.createQuery(query, Unavailabilities.class)
                .getSingleResult();
        return vtbUnavailability;
    }

    @Override
    public List<Unavailabilities> getVtbUnavailabilities(String[] faultIds, String[] serviceIds) {

        var requestParam = Streams.zip(Arrays.stream(faultIds), Arrays.stream(serviceIds),
                (faultId, serviceId) -> String.format("(%s, %s)", faultId, serviceId))
                .collect(Collectors.joining(", "));


        String query =  String.format("select unavailabilities from VtbUnavailabilities unavailabilities where (fault_id, service_id) in(%s);", requestParam);
        List<Unavailabilities> vtbUnavailability = entityManager.createQuery(query, Unavailabilities.class)
                .getResultList();
        return vtbUnavailability;
    }

    @Override
    @Transactional
    public void putModels(List<Unavailabilities> models) {
        List<Unavailabilities> unavailabilities = availabilitiesRepo.findByFaultIdInAndServiceIdIn(
                models
                        .stream()
                        .map(Unavailabilities::getFaultId)
                        .collect(Collectors.toList()),
                models
                        .stream()
                        .map(Unavailabilities::getServiceId)
                        .collect(Collectors.toList()));
        log.info("unavailabilities founded: {}", unavailabilities.size());

        Map<Boolean, List<Unavailabilities>> groups = models
                .stream()
                .collect(Collectors.partitioningBy(unavailabilities::contains));
        log.info("unavailabilities for update: {}", groups.get(Boolean.FALSE).size());
        log.info("unavailabilities for insert: {}", groups.get(Boolean.TRUE).size());

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
