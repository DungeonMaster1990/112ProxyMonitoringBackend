package ru.vtb.monitoring.vtb112.db.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;

import java.util.List;

@Repository
public interface UnavailabilitiesRepository extends JpaRepository<Unavailabilities, Integer>, UnavailabilitiesRepositoryCustom {

    List<Unavailabilities> findByFaultIdInAndServiceIdIn(List<String> faultIds, List<String> serviceIds);

}