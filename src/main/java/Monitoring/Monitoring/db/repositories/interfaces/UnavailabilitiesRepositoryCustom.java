package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Unavailabilities;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnavailabilitiesRepositoryCustom extends SmRepository<Unavailabilities> {

    List<Unavailabilities> getAllVtbUnavailabilities();

    Unavailabilities getVtbUnavailability(int id);

    List<Unavailabilities> getVtbUnavailabilities(String[] faultIds, String[] serviceIds);

}
