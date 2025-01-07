package me.jjorae.health_activity_collector.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jjorae.health_activity_collector.constants.Constants;
import me.jjorae.health_activity_collector.dto.StatisticsDto;
import me.jjorae.health_activity_collector.service.StatisticsService;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 통계 조회 관련 API 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping(value = {"/daily", "/daily/{recordKey}"})
    public List<StatisticsDto> dailyStatistics(@PathVariable(required = false) String recordKey) {
        return statisticsService.getDailyStatistics(StringUtils.hasLength(recordKey) ? recordKey : Constants.CACHE_ALL_KEY).getStatistics();
    }

    @GetMapping(value = {"/monthly", "/monthly/{recordKey}"})
    public List<StatisticsDto> monthlyStatistics(@PathVariable(required = false) String recordKey) {
        return statisticsService.getMonthlyStatistics(StringUtils.hasLength(recordKey) ? recordKey : Constants.CACHE_ALL_KEY).getStatistics();
    }
}
