package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Incidents;

import java.util.List;

public interface VtbIncidentsRepository {
    List<Incidents> getAllVtbIncidents();

    List<Incidents> getTimeFilteredVtbIncidents(long daysDiff);

    void putVtbIncidents(List<Incidents> incidents);

    Incidents getVtbIncident(int id);

    public List<Incidents> getVtbIncidents(List<String> incidentIds);
}
