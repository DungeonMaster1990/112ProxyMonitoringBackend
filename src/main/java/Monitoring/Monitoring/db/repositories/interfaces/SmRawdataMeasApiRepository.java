package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.SmDefMeasurementApi;
import Monitoring.Monitoring.db.models.SmRawdataMeasApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmRawdataMeasApiRepository extends JpaRepository<SmRawdataMeasApi, Integer> {
}
