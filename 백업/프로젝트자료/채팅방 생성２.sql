-- 채팅방 생성.sql

-- ══════════════════════════════════════════
-- 기존 테이블 삭제
-- ══════════════════════════════════════════
DROP TABLE IF EXISTS chat_messages;
DROP TABLE IF EXISTS chat_rooms;

-- ══════════════════════════════════════════
-- 채팅방 테이블
-- ══════════════════════════════════════════
CREATE TABLE chat_rooms (
    id              VARCHAR(36)  PRIMARY KEY,
    hospital_id     VARCHAR(100) NOT NULL,
    hospital_name   VARCHAR(100) NOT NULL,
    dept            VARCHAR(50),
    avatar          VARCHAR(10),
    patient_id      VARCHAR(100) NOT NULL,
    last_msg        TEXT,
    last_time       VARCHAR(10),
    unread          INT          NOT NULL DEFAULT 0,   -- 레거시 (사용 안 함)
    patient_unread  INT          NOT NULL DEFAULT 0,   -- 환자가 읽지 않은 메시지 수
    hospital_unread INT          NOT NULL DEFAULT 0,   -- 병원이 읽지 않은 메시지 수
    created_at      DATETIME     DEFAULT NOW(),
    UNIQUE KEY uq_patient_hospital (patient_id, hospital_id)  -- 중복 방지
);

-- ══════════════════════════════════════════
-- 메시지 테이블
-- ══════════════════════════════════════════
CREATE TABLE chat_messages (
    id          VARCHAR(36)  PRIMARY KEY,
    room_id     VARCHAR(36)  NOT NULL,
    `from`      VARCHAR(20)  NOT NULL,   -- 'user' or 'hospital_1' 형식
    sender_name VARCHAR(100),
    text        TEXT         NOT NULL,
    time        VARCHAR(10),
    created_at  DATETIME     DEFAULT NOW(),
    INDEX idx_room_id (room_id)          -- 조회 성능
);
