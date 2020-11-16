package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Incidents;

import java.util.List;
import java.util.Set;

public interface IncidentsRepository extends SmRepository<Incidents> {
    List<Incidents> getAllVtbIncidents();

    List<Incidents> getTimeFilteredVtbIncidents(long daysDiff);

    Incidents getVtbIncident(int id);

    List<Incidents> getVtbIncidents(List<String> incidentIds);

    List<Incidents> getTimeFilteredNonSentVtbIncidents(long daysDiff);

    void markAsNotificationSent(Set<Integer> incidentsIds);
}
