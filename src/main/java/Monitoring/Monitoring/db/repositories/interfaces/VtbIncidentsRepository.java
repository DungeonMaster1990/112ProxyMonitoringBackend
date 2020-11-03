package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.VtbIncidents;

import java.util.List;

public interface VtbIncidentsRepository {
    List<VtbIncidents> getAllVtbIncidents();

    List<VtbIncidents> getTimeFilteredVtbIncidents(long daysDiff);

    void putVtbIncidents(List<VtbIncidents> vtbIncidents);

    VtbIncidents getVtbIncident(int id);

    public List<VtbIncidents> getVtbIncidents(List<String> incidentIds);
}
