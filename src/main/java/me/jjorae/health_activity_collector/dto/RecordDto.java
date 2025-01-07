package me.jjorae.health_activity_collector.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {
    private String recordkey;
    private RecordData data;
    private String lastUpdate;
    private String type;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecordData {
        private List<RecordDto.Entry> entries;
        private RecordDto.Source source;
        private String memo;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Entry {
        private Period period;
        private Distance distance;
        private Calories calories;
        // 기기 별 전달되는 데이터 형이 달라서 String으로 받아서 처리
        private String steps;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Period {
        // 기기 별 전달되는 날짜 포맷이 달라서 String으로 받아서 처리
        private String from;
        private String to;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Distance {
        private String unit;
        private Double value;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Calories {
        private String unit;
        private Double value;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Source {
        private Integer mode;
        private Product product;
        private String name;
        private String type;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String name;
        private String vender;
    }
}
