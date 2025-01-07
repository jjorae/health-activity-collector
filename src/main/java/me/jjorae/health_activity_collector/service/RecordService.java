package me.jjorae.health_activity_collector.service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jjorae.health_activity_collector.constants.Constants;
import me.jjorae.health_activity_collector.domain.Entry;
import me.jjorae.health_activity_collector.domain.Record;
import me.jjorae.health_activity_collector.dto.RecordDto;
import me.jjorae.health_activity_collector.dto.RecordDto.Product;
import me.jjorae.health_activity_collector.dto.RecordDto.RecordData;
import me.jjorae.health_activity_collector.dto.RecordDto.Source;
import me.jjorae.health_activity_collector.repository.RecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {
    private final RecordRepository recordRepository;

    @Transactional
    public void saveRecord(RecordDto recordDto) {
        // 각 Entry가 Record를 참조할 수 있도록 Entries를 할당하기 전에 Record 엔티티를 생성
        Record record = convertToRecordEntity(recordDto);
        record.setEntries(convertToEntryEntity(recordDto.getData()));
        recordRepository.save(record);
        log.info("Saved record. {}", record.getRecordKey());
    }

    // 간단한 샘플 코드라 convert 함수를 여기에 선언
    private Record convertToRecordEntity(RecordDto recordDto) {
        RecordData recordData = recordDto.getData();
        Source source = recordData.getSource();
        Product product = source.getProduct();

        return Record.builder()
            .recordKey(recordDto.getRecordkey())
            .productName(product.getName())
            .productVender(product.getVender())
            .sourceMode(source.getMode())
            .sourceName(source.getName())
            .sourceType(source.getType())
            .lastUpdate(parseDateTime(recordDto.getLastUpdate()))
            .type(recordDto.getType())
            .build();
    }

    private List<Entry> convertToEntryEntity(RecordData recordData) {
        return recordData.getEntries()
            .stream()
            .map(entry -> Entry.builder()
                .periodFrom(parseDateTime(entry.getPeriod().getFrom()))
                .periodTo(parseDateTime(entry.getPeriod().getTo()))
                .distanceUnit(entry.getDistance().getUnit())
                .distanceValue(entry.getDistance().getValue())
                .caloriesUnit(entry.getCalories().getUnit())
                .caloriesValue(entry.getCalories().getValue())
                .steps(Double.valueOf(entry.getSteps()))
                .build()
            )
            .toList();
    }

    private OffsetDateTime parseDateTime(String dateTimeString) {
        // 시간대 정보가 없는 경우 다른 기기 값에 맞춰 +0000 추가
        if (!dateTimeString.contains("+")) {
            dateTimeString += " +0000";
        }

        for (DateTimeFormatter formatter : Constants.FORMATTERS) {
            try {
                return OffsetDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                // 파싱 실패할 경우 다음 formatter 사용
            }
        }
        // 등록된 formatter 모두 파싱 실패한 경우 에러 발생
        throw new IllegalArgumentException("지원하지 않는 날짜 형태입니다. - " + dateTimeString);
    }
}
