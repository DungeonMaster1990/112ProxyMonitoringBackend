package ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface SmRawDataMeasVerticaRepository extends JpaRepository<SmRawdataMeasVertica, Integer> {
    List<SmRawdataMeasVertica> findByStatusIdAndTimeStampGreaterThanAndMeasurementIdIn(Integer statusId,
                                                                                       ZonedDateTime timeStamp,
                                                                                       List<Integer> measurementIds,
                                                                                       Pageable pageable);
}
