package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.Metrics;

import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Integer> {

    List<Metrics> findByIsMerged(boolean isMerged);

}
