package ru.vtb.monitoring.vtb112.db.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Incident;

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
}
