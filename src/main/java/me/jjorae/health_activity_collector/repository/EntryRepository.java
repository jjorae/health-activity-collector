package me.jjorae.health_activity_collector.repository;

import java.util.List;
import me.jjorae.health_activity_collector.domain.Entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    // JPQL에서 DTO를 직접 반환하려 할 때 생성자를 통해 리턴하는 경우 타입 불일치 등의 에러가 발생하여 interface-based projection 방식으로 사용
    @Query("""
        SELECT 
            DATE_FORMAT(e.periodFrom, '%Y-%m-%d') as date,
            SUM(e.steps) as totalSteps,
            SUM(e.caloriesValue) as totalCalories,
            SUM(e.distanceValue) as totalDistance,
            e.record.recordKey as recordKey
        FROM Entry e
        WHERE e.record.recordKey = :recordKey
        GROUP BY DATE_FORMAT(e.periodFrom, '%Y-%m-%d'), e.record.recordKey
        ORDER BY DATE_FORMAT(e.periodFrom, '%Y-%m-%d')
    """)
    List<Statistics> dailyStatistics(@Param("recordKey") String recordKey);

    @Query("""
        SELECT 
            DATE_FORMAT(e.periodFrom, '%Y-%m-%d') as date,
            SUM(e.steps) as totalSteps,
            SUM(e.caloriesValue) as totalCalories,
            SUM(e.distanceValue) as totalDistance,
            e.record.recordKey as recordKey
        FROM Entry e
        GROUP BY DATE_FORMAT(e.periodFrom, '%Y-%m-%d'), e.record.recordKey
        ORDER BY DATE_FORMAT(e.periodFrom, '%Y-%m-%d'), e.record.recordKey
    """)
    List<Statistics> dailyStatistics();

    @Query("""
        SELECT 
            DATE_FORMAT(e.periodFrom, '%Y-%m') as date,
            SUM(e.steps) as totalSteps,
            SUM(e.caloriesValue) as totalCalories,
            SUM(e.distanceValue) as totalDistance,
            e.record.recordKey as recordKey
        FROM Entry e 
        WHERE e.record.recordKey = :recordKey
        GROUP BY DATE_FORMAT(e.periodFrom, '%Y-%m'), e.record.recordKey
        ORDER BY DATE_FORMAT(e.periodFrom, '%Y-%m')
    """)
    List<Statistics> monthlyStatistics(@Param("recordKey") String recordKey);

    @Query("""
        SELECT 
            DATE_FORMAT(e.periodFrom, '%Y-%m') as date,
            SUM(e.steps) as totalSteps,
            SUM(e.caloriesValue) as totalCalories,
            SUM(e.distanceValue) as totalDistance,
            e.record.recordKey as recordKey
        FROM Entry e 
        GROUP BY DATE_FORMAT(e.periodFrom, '%Y-%m'), e.record.recordKey
        ORDER BY DATE_FORMAT(e.periodFrom, '%Y-%m'), e.record.recordKey
    """)
    List<Statistics> monthlyStatistics();
}
