package com.fc.pass.repository.statistics;

import com.fc.pass.service.statistics.AggregatedStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Integer> {

    @Query("""
        SELECT new com.fc.pass.service.statistics.AggregatedStatistics(
            s.statisticsAt,
            SUM(s.allCount),
            SUM(s.attendedCount),
            SUM(s.cancelledCount))
          FROM StatisticsEntity s
         WHERE s.statisticsAt BETWEEN :from AND :to
         GROUP BY s.statisticsAt""")
    List<AggregatedStatistics> findByStatisticsAtBetweenAndGroupBy(LocalDateTime from, LocalDateTime to);

}
