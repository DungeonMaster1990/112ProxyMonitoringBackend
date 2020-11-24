package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.dto.GroupedChanges;
import Monitoring.Monitoring.db.models.Changes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ChangesRepository extends JpaRepository<Changes, Integer>, ChangesRepositoryCustom {

    List<Changes> findByChangeIdIn(List<String> changesIds);

    @Query("""
                select new Monitoring.Monitoring.db.models.dto.GroupedChanges(c.category, count(c.category))
                from Changes c
                where c.plannedStartAt >= :startDate
                  and c.plannedEndAt  <= :endDate
                group by c.category
          """)
    List<GroupedChanges> getGroupedChanges(@Param("startDate") ZonedDateTime startDate,
                                           @Param("endDate") ZonedDateTime endDate);

    List<Changes> findByCategoryAndChangeIdContaining(String category, String keyword, Pageable paging);

}
