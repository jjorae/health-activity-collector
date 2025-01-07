-- record: 사용자의 활동 기록을 저장하는 메인 테이블
-- 각 레코드는 Samsung Health 등의 앱에서 수집된 데이터를 나타냅니다.
CREATE TABLE `record` (
  `record_key` varchar(50) NOT NULL,           -- 전달 받는 recordkey가 고유한 값라고 생각해서 PK로 선언
  `last_update` datetime(6) DEFAULT NULL,       -- 마지막 데이터 업데이트 시간
  `type` varchar(20) DEFAULT NULL,              -- 활동 유형 (예: steps 등)
  `memo` varchar(255) DEFAULT NULL,             -- 메모
  `product_name` varchar(100) DEFAULT NULL,     -- 데이터 수집 기기/앱 이름 (예: Android)
  `product_vender` varchar(100) DEFAULT NULL,   -- 제조사 이름 (예: Samsung)
  `source_mode` int DEFAULT NULL,               -- 데이터 소스 모드
  `source_name` varchar(100) DEFAULT NULL,      -- 데이터 소스 이름 (예: SamsungHealth)
  `source_type` varchar(100) DEFAULT NULL,      -- 데이터 소스 타입 정보
  `created_at` datetime DEFAULT NULL,           -- 레코드 생성 시간(JPA Auditing으로 자동 추가)
  PRIMARY KEY (`record_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- entry: 세부 활동 데이터를 저장하는 테이블
CREATE TABLE `entry` (
  `id` bigint NOT NULL AUTO_INCREMENT,          -- PK
  `period_from` datetime(6) DEFAULT NULL,       -- 데이터 수집 시작 시간
  `period_to` datetime(6) DEFAULT NULL,         -- 데이터 수집 종료 시간
  `calories_unit` varchar(20) DEFAULT NULL,     -- 칼로리 단위 (예: kcal)
  `calories_value` double DEFAULT NULL,         -- 소모 칼로리
  `distance_unit` varchar(20) DEFAULT NULL,     -- 거리 단위 (예: km)
  `distance_value` double DEFAULT NULL,         -- 이동 거리
  `steps` double DEFAULT NULL,                  -- 걸음 수
  `record_key` varchar(50) DEFAULT NULL,        -- record 테이블 key
  `created_at` datetime DEFAULT NULL,           -- 레코드 생성 시간(JPA Auditing으로 자동 추가)
  PRIMARY KEY (`id`),
  KEY `FKbcyvrsxqms54ri33dbmm4htg3` (`record_key`),
  CONSTRAINT `FKbcyvrsxqms54ri33dbmm4htg3` FOREIGN KEY (`record_key`) REFERENCES `record` (`record_key`)
) ENGINE=InnoDB AUTO_INCREMENT=4711 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 인덱스 생성
-- 활동 유형별 검색 최적화
CREATE INDEX idx_record_type ON record(type);

-- 기간별 데이터 조회 최적화
CREATE INDEX idx_entry_period ON entry(period_from, period_to);
