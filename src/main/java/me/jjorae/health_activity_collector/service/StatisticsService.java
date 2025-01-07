package me.jjorae.health_activity_collector.service;

import lombok.RequiredArgsConstructor;
import me.jjorae.health_activity_collector.constants.Constants;
import me.jjorae.health_activity_collector.dto.StatisticsDto;
import me.jjorae.health_activity_collector.dto.StatisticsListDto;
import me.jjorae.health_activity_collector.repository.EntryRepository;
import me.jjorae.health_activity_collector.repository.Statistics;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final EntryRepository entryRepository;

    // List<>를 그대로 캐싱할 경우 최초 접근 시 캐시에 저장은 잘 되지만 재접근 시 캐시된 데이터 역직렬화 시 에러 발생으로 wrapper클래스 생성 후 사용
    @Cacheable(value = "dailyStatistics", key = "#recordKey", unless = "#result == null")
    public StatisticsListDto getDailyStatistics(String recordKey) {
        return new StatisticsListDto(
            convertToDto(
                Constants.CACHE_ALL_KEY.equals(recordKey) ?
                    entryRepository.dailyStatistics() : entryRepository.dailyStatistics(recordKey)
            )
        );
    }

    @Cacheable(value = "monthlyStatistics", key = "#recordKey", unless = "#result == null")
    public StatisticsListDto getMonthlyStatistics(String recordKey) {
        return new StatisticsListDto(
            convertToDto(
                Constants.CACHE_ALL_KEY.equals(recordKey) ?
                    entryRepository.monthlyStatistics() : entryRepository.monthlyStatistics(recordKey)
            )
        );
    }

    // 캐시된 정보에 접근하려 할 때 JPQL에서 DTO 반환을 위해 사용한 인터페이스를 그대로 사용할 경우 역직렬화 시 에러 발생으로 다시 DTO로 변환
    private List<StatisticsDto> convertToDto(List<Statistics> statistics) {
        return statistics.stream()
        .map(stats -> new StatisticsDto(
                stats.getDate(), stats.getTotalSteps(), stats.getTotalCalories(), stats.getTotalDistance(), stats.getRecordKey()
            )
        )
        .toList();
    }
}
