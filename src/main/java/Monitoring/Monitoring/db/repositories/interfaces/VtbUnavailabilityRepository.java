package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.VtbIncidents;
import Monitoring.Monitoring.db.models.VtbUnavailability;

import java.util.List;

public interface VtbUnavailabilityRepository {
    List<VtbUnavailability> getAllVtbUnavailabilities();

    void putVtbUnavailabilities(List<VtbUnavailability> vtbIncidents);

    VtbUnavailability getVtbUnavailability(int id);
}
