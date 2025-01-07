package me.jjorae.health_activity_collector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {
    private String date;
    private Double totalSteps;
    private Double totalCalories;
    private Double totalDistance;
    private String recordKey;
}
