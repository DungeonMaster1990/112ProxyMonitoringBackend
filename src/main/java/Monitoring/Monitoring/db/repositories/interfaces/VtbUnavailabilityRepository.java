package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Unavailabilities;

import java.util.List;

public interface VtbUnavailabilityRepository {
    List<Unavailabilities> getAllVtbUnavailabilities();

    void putVtbUnavailabilities(List<Unavailabilities> vtbIncidents);

    Unavailabilities getVtbUnavailability(int id);

    List<Unavailabilities> getVtbUnavailabilities(String[] faultIds, String[] serviceIds);
}
