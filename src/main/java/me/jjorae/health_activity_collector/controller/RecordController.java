package me.jjorae.health_activity_collector.controller;

import lombok.RequiredArgsConstructor;
import me.jjorae.health_activity_collector.dto.RecordDto;
import me.jjorae.health_activity_collector.external.kafka.producer.HealthActivityProducer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 고객건강활동 정보 수집 API 관련 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final HealthActivityProducer healthActivityProducer;

    @PostMapping
    public void addRecord(@RequestBody RecordDto recordDto) {
        // 전달 받은 JSON을 producer를 통해 메시지 발행
        healthActivityProducer.send(recordDto.getRecordkey(), recordDto);
    }
}
