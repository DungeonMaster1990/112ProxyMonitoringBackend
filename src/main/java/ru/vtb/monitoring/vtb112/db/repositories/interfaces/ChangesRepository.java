package ru.vtb.monitoring.vtb112.db.repositories.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.Changes;
import ru.vtb.monitoring.vtb112.db.models.dto.GroupedChanges;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ChangesRepository extends JpaRepository<Changes, Integer>, ChangesRepositoryCustom {

    List<Changes> findByChangeIdIn(List<String> changesIds);

    @Query("""
                  select new ru.vtb.monitoring.vtb112.db.models.dto.GroupedChanges(c.category, count(c.category))
                  from Changes c
                  where c.plannedStartAt >= :startDate
                    and c.plannedEndAt  <= :endDate
                  group by c.category
            """)
    List<GroupedChanges> getGroupedChanges(@Param("startDate") ZonedDateTime startDate,
                                           @Param("endDate") ZonedDateTime endDate);

    List<Changes> findByCategoryAndChangeIdContaining(String category, String keyword, Pageable paging);

}
