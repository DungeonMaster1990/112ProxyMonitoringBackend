package ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmDefMeasurementVertica;

@Repository
public interface SmDefMeasurementVerticaRepository
        extends JpaRepository<SmDefMeasurementVertica, Integer>,
        SmDefMeasurementVerticaRepositoryCustom {
}
