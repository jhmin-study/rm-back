# 프로젝트명: rm

## 프로젝트 목표: 
소상공인들의 자원관리 편이성을 위해 자원관리 시스템을 지원해준다. 
ERP 프로그램 사용이 부담되거나 컴퓨터 사용에 익숙하지 않은 소상공인을 위한 자원 관리 서비스

## 팀원 
 - 민주현
 - 김명용
 - 변재영
 - 반유진
 - 배상엽
 - 김재용

## SQL DDL
 
```
DROP TABLE IF EXISTS rm_user;
CREATE TABLE rm_user (
    user_id VARCHAR(255) PRIMARY KEY,           -- 이메일 ID
    password VARCHAR(255) NOT NULL,             -- 현재 비밀번호
    last_password VARCHAR(255),                 -- 이전 비밀번호
    user_nm VARCHAR(255) NOT NULL,              -- 회원이름
    user_phno VARCHAR(255),                     -- 회원휴대전화번호("-" 없이 문자열로 기재)
    quit_yn CHAR(1),                            -- 탈퇴 여부 ('Y' 또는 NULL)
    quit_dt DATETIME,                           -- 탈퇴 일시
    signup_dt DATETIME DEFAULT CURRENT_TIMESTAMP, -- 가입 일자 (기본값: 현재일시)
    create_dt DATETIME DEFAULT CURRENT_TIMESTAMP, -- 최초 등록일시
    update_dt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 최종 수정일시
);

DROP TABLE IF EXISTS rm_workplace;
CREATE TABLE rm_workplace (
    workplace_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,  -- 타입 수정: rm_user.user_id와 일치해야 함
    business_type_nm VARCHAR(100) NOT NULL,
    business_reg_no VARCHAR(20) NOT NULL UNIQUE,
    business_name VARCHAR(100) NOT NULL,
    owner_name VARCHAR(50),
    phone_number VARCHAR(20),
    address VARCHAR(255) NOT NULL,
    detail_address VARCHAR(255),
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES rm_user(user_id)
);


DROP TABLE IF EXISTS rm_resource;
CREATE TABLE rm_resource(
   	workplace_id BIGINT,
	resource_id BIGINT primary key auto_increment,
	resource_name VARCHAR(255) NOT NULL,
	place VARCHAR(255) NOT NULL,
	created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT fk_workplace foreign key(workplace_id) REFERENCES rm_workplace (workplace_id)
);
  
  
DROP TABLE IF EXISTS rm_usage; 
CREATE TABLE rm_usage(
	usage_id BIGINT primary key AUTO_INCREMENT,
   	resource_id BIGINT,
	usage_status VARCHAR(255) NOT NULL,
     	resource_user_name VARCHAR(255) NOT NULL,
      	resource_user_phone VARCHAR(255) NOT NULL,
      	resource_user_email VARCHAR(255),
      	resource_user_note TEXT,
      	usage_st DATE NOT NULL,
    	usage_ed DATE NOT NULL,
      	CONSTRAINT fk_resource foreign key(resource_id) REFERENCES rm_resource(resource_id)
);
		
```
