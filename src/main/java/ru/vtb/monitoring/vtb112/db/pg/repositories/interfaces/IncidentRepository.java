package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedIncidents;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer>, IncidentRepositoryCustom {

    @Query(value = """
            select * from monitoring.incidents 
            where created_at = (select max(created_at)from monitoring.incidents)
            LIMIT 1""", nativeQuery = true)
    Optional<Incident> findMaxByCreatedDate();

    List<Incident> findByIncidentIdIn(List<String> incidentIds);

    @Query("""
              select new ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedIncidents(
                     SUM(case when (i.priority = '1') then 1 else 0 end),
                     SUM(case when (i.priority = '2') then 1 else 0 end),
                     SUM(case when (i.priority = '3') then 1 else 0 end))
                from Incident i
               where i.status is not null
                 and i.status not in ('Закрыто', 'Завершено')
            """)
    List<GroupedIncidents> countActiveIncidents();

    @Query("""
              select i.failurePoint
                from Incident i
               where i.failurePoint is not null
                 and i.status is not null
                 and i.status not in ('Закрыто', 'Завершено')
            group by i.failurePoint
            order by count(i.failurePoint) desc
            """)
    List<String> getFailurePoints(Pageable pageable);
}
