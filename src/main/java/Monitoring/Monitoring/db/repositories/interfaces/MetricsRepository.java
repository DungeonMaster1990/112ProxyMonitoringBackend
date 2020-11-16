package Monitoring.Monitoring.db.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Monitoring.Monitoring.db.models.Metrics;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Integer> {

}
