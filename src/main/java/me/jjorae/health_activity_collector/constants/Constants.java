package me.jjorae.health_activity_collector.constants;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String TOPIC = "health_activity";
    public static final String CONSUMER_GROUP_ID = "health_activity_consumer_group";
    public static final String CACHE_ALL_KEY = "ALL";
    // JSON으로 전달되는 날짜 값들의 형태가 추가될 경우 FORMATTERS에 추가 정의
    public static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss xxxx"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss xxxx"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxxx"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    );
}
