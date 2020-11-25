package ru.vtb.monitoring.vtb112.db.repositories.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Incident;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface IncidentRepositoryCustom extends  SmRepository<Incident> {

    List<Incident> getAllVtbIncidents();

    List<Incident> getTimeFilteredVtbIncidents(long daysDiff);

    void putVtbIncidents(List<Incident> incidents);

    Incident getVtbIncident(int id);

    List<Incident> getVtbIncidents(List<String> incidentIds);

    List<Incident> getTimeFilteredNonSentVtbIncidents(long daysDiff);

    void markAsNotificationSent(Set<Integer> incidentsIds);

    List<Incident> allByCriteria(List<String> affectedSystems, ZonedDateTime startDate, String keyword, Pageable paging);
}
