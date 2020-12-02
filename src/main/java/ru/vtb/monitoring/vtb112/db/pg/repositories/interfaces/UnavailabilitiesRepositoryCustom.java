package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;

import java.util.List;

@Repository
public interface UnavailabilitiesRepositoryCustom extends SmRepository<Unavailabilities> {

    List<Unavailabilities> getAllVtbUnavailabilities();

    Unavailabilities getVtbUnavailability(int id);

    List<Unavailabilities> getVtbUnavailabilities(String[] faultIds, String[] serviceIds);

}
