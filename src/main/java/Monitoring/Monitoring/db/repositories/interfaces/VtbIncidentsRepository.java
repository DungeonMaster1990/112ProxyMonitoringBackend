package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.VtbIncidents;

import java.util.List;

public interface VtbIncidentsRepository {
    List<VtbIncidents> getAllVtbIncidents();

    void putVtbIncidents(List<VtbIncidents> vtbIncidents);

    VtbIncidents getVtbIncident(int id);
}
