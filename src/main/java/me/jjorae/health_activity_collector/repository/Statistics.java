package me.jjorae.health_activity_collector.repository;

public interface Statistics {
    String getDate();
    Double getTotalSteps();
    Double getTotalCalories();
    Double getTotalDistance();
    String getRecordKey();
}
