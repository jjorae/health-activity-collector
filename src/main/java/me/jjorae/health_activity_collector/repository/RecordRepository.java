package me.jjorae.health_activity_collector.repository;

import me.jjorae.health_activity_collector.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, String> {

}
