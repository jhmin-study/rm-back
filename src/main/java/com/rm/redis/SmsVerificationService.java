package com.rm.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.rm.dto.user.UserDTO;

@Service
public class SmsVerificationService {

	RandomAuthUtil randomAuthUtil;
	RedisTemplate<String, String> redisTemplate;
	
	
	
	
	public SmsVerificationService(RandomAuthUtil randomAuthUtil, RedisTemplate<String, String> redisTemplate) {
		this.randomAuthUtil = randomAuthUtil;
		this.redisTemplate = redisTemplate;
	}



	public void sendVerificationCode(UserDTO dto) {
		
		String id = dto.getUserId();
		String phone = dto.getUserPhno();
		String code = randomAuthUtil.generateRandomKey();
		
		// redis에 인증 정보 저장, 유효기간 설정
		redisTemplate.opsForValue().set(phone, code, 3, TimeUnit.MINUTES);
		
		// 문자메시지로 뿌려주기(콘솔로 대체)
		System.out.println("인증 코드 : " + code);
	
	}
}
