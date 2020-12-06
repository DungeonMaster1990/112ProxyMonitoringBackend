package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.Changes;
import ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedChanges;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface ChangesRepository extends JpaRepository<Changes, Integer>, ChangesRepositoryCustom {

    List<Changes> findByChangeIdIn(List<String> changesIds);

    @Query("""
                  select new ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedChanges(c.category, count(c.category))
                  from Changes c
                  where c.plannedStartAt >= :startDate
                    and c.plannedStartAt <= :endDate
                    and c.currentPhase in (:phases)
                    and c.category is not null
                  group by c.category
            """)
    List<GroupedChanges> getPlannedGroupedChanges(@Param("startDate") ZonedDateTime startDate,
                                                  @Param("endDate") ZonedDateTime endDate,
                                                  @Param("phases") Collection<String> phases);

    @Query("""
                  select new ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedChanges(c.category, count(c.category))
                  from Changes c
                  where c.plannedStartAt <= :now 
                    and c.plannedEndAt >= :now
                    and c.currentPhase in (:phases)
                    and c.category is not null
                  group by c.category
            """)
    List<GroupedChanges> getCurrentGroupedChanges(@Param("now") ZonedDateTime now,
                                                  @Param("phases") Collection<String> phases);

    List<Changes> findByCategoryAndChangeIdContaining(String category, String keyword, Pageable paging);

    List<Changes> findByCategory(String category, Pageable paging);

}
