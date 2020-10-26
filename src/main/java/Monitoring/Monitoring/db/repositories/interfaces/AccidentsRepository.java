package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Accident;
import org.springframework.data.repository.CrudRepository;

public interface AccidentsRepository extends CrudRepository<Accident, Long> {
}
