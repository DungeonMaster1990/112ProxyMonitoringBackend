package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer>, IncidentRepositoryCustom {

    @Query(value = """
            select * from monitoring.incidents 
            where created_at = (select max(created_at)from monitoring.incidents)
            LIMIT 1""", nativeQuery = true)
    Optional<Incident> findMaxByCreatedDate();
}
