package me.jjorae.health_activity_collector.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA에 필요한 설정
 */
@Configuration
@EnableJpaAuditing // JpaAuditing을 사용하기 위한 설정
public class JpaConfig {

}
