package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Incidents;

import java.util.List;
import java.util.Set;

public interface IncidentsRepository {
    List<Incidents> getAllVtbIncidents();

    List<Incidents> getTimeFilteredVtbIncidents(long daysDiff);

    void putVtbIncidents(List<Incidents> incidents);

    Incidents getVtbIncident(int id);

    List<Incidents> getVtbIncidents(List<String> incidentIds);

    List<Incidents> getTimeFilteredNonSentVtbIncidents(long daysDiff);

    void markAsNotificationSent(Set<Integer> incidentsIds);
}
