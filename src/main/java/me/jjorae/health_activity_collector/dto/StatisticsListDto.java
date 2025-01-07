package me.jjorae.health_activity_collector.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsListDto {
    private List<StatisticsDto> statistics;
}
