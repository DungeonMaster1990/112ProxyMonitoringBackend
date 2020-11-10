package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.VtbIncidents;

import java.util.List;
import java.util.Set;

public interface VtbIncidentsRepository {
    List<VtbIncidents> getAllVtbIncidents();

    List<VtbIncidents> getTimeFilteredNonSentVtbIncidents(long daysDiff);

    List<VtbIncidents> getTimeFilteredVtbIncidents(long daysDiff);

    void putVtbIncidents(List<VtbIncidents> vtbIncidents);

    VtbIncidents getVtbIncident(int id);

    public List<VtbIncidents> getVtbIncidents(List<String> incidentIds);

    void markAsNotificationSent(Set<Integer> collect);
}
