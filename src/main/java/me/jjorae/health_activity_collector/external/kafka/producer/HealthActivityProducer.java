package me.jjorae.health_activity_collector.external.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jjorae.health_activity_collector.constants.Constants;
import me.jjorae.health_activity_collector.dto.RecordDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HealthActivityProducer {
    private final KafkaTemplate<String, RecordDto> kafkaTemplate;

    public void send(String key, RecordDto recordDto) {
        kafkaTemplate.send(Constants.TOPIC, key, recordDto);
        log.info("Send to kafka. {}", key);
    }
}
