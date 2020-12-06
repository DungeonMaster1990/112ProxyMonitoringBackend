package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedUnavailabilities;

import java.util.List;

@Repository
public interface UnavailabilitiesRepository extends JpaRepository<Unavailabilities, Integer>, UnavailabilitiesRepositoryCustom {

    List<Unavailabilities> findByFaultIdInAndServiceIdIn(List<String> faultIds, List<String> serviceIds);

    @Query("""
              select new ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedUnavailabilities(u.serviceName, count(u.serviceName))
                from Unavailabilities u
                join Incident i
                  on u.faultId = i.incidentId
               where i.status is not null
                 and i.status not in ('Закрыто', 'Завершено')
              group by u.serviceName
              order by count(u.serviceName) desc
            """)
    List<GroupedUnavailabilities> getTopUnavailabilities(Pageable pageable);

}