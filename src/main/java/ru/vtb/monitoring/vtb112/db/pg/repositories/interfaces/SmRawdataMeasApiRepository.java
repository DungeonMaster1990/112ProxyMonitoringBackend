package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.SmRawdataMeasApi;

@Repository
public interface SmRawdataMeasApiRepository extends JpaRepository<SmRawdataMeasApi, Integer> {
}
