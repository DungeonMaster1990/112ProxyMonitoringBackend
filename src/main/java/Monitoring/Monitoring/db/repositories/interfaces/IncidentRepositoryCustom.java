package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Incident;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface IncidentRepositoryCustom {

    List<Incident> getAllVtbIncidents();

    List<Incident> getTimeFilteredVtbIncidents(long daysDiff);

    void putVtbIncidents(List<Incident> incidents);

    Incident getVtbIncident(int id);

    List<Incident> getVtbIncidents(List<String> incidentIds);

    List<Incident> getTimeFilteredNonSentVtbIncidents(long daysDiff);

    void markAsNotificationSent(Set<Integer> incidentsIds);

    List<Incident> allByCriteria(List<String> affectedSystems, ZonedDateTime startDate, String keyword, Pageable paging);
}
