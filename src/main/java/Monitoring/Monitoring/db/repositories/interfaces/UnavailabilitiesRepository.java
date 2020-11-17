package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Unavailabilities;

import java.util.List;

public interface UnavailabilitiesRepository extends SmRepository<Unavailabilities>{
    List<Unavailabilities> getAllVtbUnavailabilities();

    Unavailabilities getVtbUnavailability(int id);

    List<Unavailabilities> getVtbUnavailabilities(String[] faultIds, String[] serviceIds);
}
