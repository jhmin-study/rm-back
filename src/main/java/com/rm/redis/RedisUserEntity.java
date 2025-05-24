package com.rm.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* *************
 * @info : Redis User Entity
 * *************
 */
@RedisHash(value = "user", timeToLive = 180) // 만료시간 : 3분
@Getter
@Setter
@ToString
@AllArgsConstructor

public class RedisUserEntity {
	
	@Id
	private String userId;
	
	private String userPhno; // 인증 전화번호
	private String authKey;  // 인증 키
}
