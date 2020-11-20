package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Unavailabilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnavailabilitiesRepository extends
        JpaRepository<Unavailabilities, Integer>,
        UnavailabilitiesRepositoryCustom {

    List<Unavailabilities> findByFaultIdInAndServiceIdIn(List<String> faultIds, List<String> serviceIds);

}