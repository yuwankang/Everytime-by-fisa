-- USERS 테이블 생성
CREATE TABLE users (
    user_id NUMBER PRIMARY KEY, -- 기본 키
    user_name VARCHAR2(100) NOT NULL, -- 사용자 이름
    user_password VARCHAR2(255) NOT NULL, -- 사용자 비밀번호
    user_email VARCHAR2(255) UNIQUE NOT NULL, -- 사용자 이메일 (고유)
    user_phone_number VARCHAR2(20), -- 사용자 전화번호
    user_nick_name VARCHAR2(50) -- 사용자 닉네임
);

-- MARKETS 테이블 생성
CREATE TABLE markets (
    market_id NUMBER PRIMARY KEY, -- 기본 키
    market_name VARCHAR2(255) NOT NULL, -- 마켓 이름
    market_location VARCHAR2(255), -- 마켓 위치
    menu CLOB -- 메뉴
);

-- PLANS 테이블 생성
CREATE TABLE plans (
    plan_id NUMBER PRIMARY KEY, -- 기본 키
    leader_id NUMBER NOT NULL, -- 리더 ID (외래 키)
    user1_id NUMBER, -- 유저1 ID (외래 키)
    user2_id NUMBER, -- 유저2 ID (외래 키)
    user3_id NUMBER, -- 유저3 ID (외래 키)
    market_id NUMBER NOT NULL, -- 마켓 ID (외래 키)
    plan_date DATE NOT NULL, -- 날짜
    CONSTRAINT fk_leader_id FOREIGN KEY (leader_id) REFERENCES users(user_id),
    CONSTRAINT fk_user1_id FOREIGN KEY (user1_id) REFERENCES users(user_id),
    CONSTRAINT fk_user2_id FOREIGN KEY (user2_id) REFERENCES users(user_id),
    CONSTRAINT fk_user3_id FOREIGN KEY (user3_id) REFERENCES users(user_id),
    CONSTRAINT fk_market_id FOREIGN KEY (market_id) REFERENCES markets(market_id)
);

-- MARKET_HISTORY 테이블 생성
CREATE TABLE market_history (
    history_id NUMBER PRIMARY KEY, -- 기본 키
    market_id NUMBER NOT NULL, -- 마켓 ID (외래 키)
    rate NUMBER(2,1) CHECK (rate BETWEEN 0 AND 5), -- 평가 점수 (0에서 5 사이)
    review CLOB, -- 리뷰
    review_date DATE NOT NULL, -- 리뷰 날짜
    CONSTRAINT fk_market_history_market_id FOREIGN KEY (market_id) REFERENCES markets(market_id)
);

-- NOTIFICATIONS 테이블 생성
CREATE TABLE notifications (
    notification_id NUMBER PRIMARY KEY, -- 기본 키
    user_id NUMBER NOT NULL, -- 사용자 ID (외래 키)
    plan_id NUMBER NOT NULL, -- 계획 ID (외래 키)
    notification_info VARCHAR2(255), -- 알림 정보
    notification_date DATE NOT NULL, -- 알림 날짜
    CONSTRAINT fk_notification_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_notification_plan_id FOREIGN KEY (plan_id) REFERENCES plans(plan_id)
);




