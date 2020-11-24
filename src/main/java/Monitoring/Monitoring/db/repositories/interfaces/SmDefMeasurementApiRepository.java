package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmDefMeasurementApiRepository extends JpaRepository<SmDefMeasurementApi, Integer> {
}
