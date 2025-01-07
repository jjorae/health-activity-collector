package me.jjorae.health_activity_collector.external.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jjorae.health_activity_collector.constants.Constants;
import me.jjorae.health_activity_collector.dto.RecordDto;
import me.jjorae.health_activity_collector.service.RecordService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HealthActivityConsumer {
    private final RecordService recordService;

    // health_activity라는 토픽을 구독하여 받은 메시지를 처리한다.
    @KafkaListener(topics = Constants.TOPIC, groupId = Constants.CONSUMER_GROUP_ID)
    public void consume(RecordDto recordDto) {
        log.info("Received. {}", recordDto.getRecordkey());
        recordService.saveRecord(recordDto);
    }
}
