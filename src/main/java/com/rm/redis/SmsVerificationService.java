package com.rm.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.rm.dto.user.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsVerificationService {

	RandomAuthUtil randomAuthUtil;
	RedisTemplate<String, Map<String, String>> redisTemplate;
	
	
	public SmsVerificationService(RandomAuthUtil randomAuthUtil, RedisTemplate<String, Map<String, String>> redisTemplate) {
		this.randomAuthUtil = randomAuthUtil;
		this.redisTemplate = redisTemplate;
	}

	public void sendVerificationCode(UserDTO dto) {
		
		String id = dto.getUserId();
		String phone = dto.getUserPhno();
		String code = randomAuthUtil.generateRandomKey();
		
		Map<String, String> authData = Map.of(
			"phone", phone,
			"code", code
		);
		
		log.info("인증 요청 : id={}, phone={}", id, phone);
		
		// redis에 인증 정보 저장, 유효기간 설정
//		redisTemplate.opsForValue().set(id, authData, 3, TimeUnit.MINUTES);
		redisTemplate.opsForHash().putAll(id, authData);
		redisTemplate.expire(id, 3, TimeUnit.MINUTES); // 3분 후 만료
		
		// 문자메시지로 뿌려주기(콘솔로 대체)
		System.out.println("인증 코드 : " + code);
	
	}

	public boolean checkAuthNo(UserDTO dto) {
		
		// userDTO의 비밀번호 필드에 입력 인증번호가 입력됨.
		String id = dto.getUserId();
		String userPhno = dto.getUserPhno();
		String inputAuthNo = dto.getPassword();
		
		Map<Object, Object> authData = new HashMap<>();
		
		log.info("인증 확인 요청 : id={}, Input Auth No={}", id, inputAuthNo);
		
		// reids에 저장된 인증 정보 조회
		authData = redisTemplate.opsForHash().entries(id);
		if (authData.isEmpty()) {
			log.warn("인증 정보가 조회하지 않습니다.");
			return false;
		}
		// 인증정보 조회 시 phone과 code 값 비교
		if (authData.get("phone").equals(userPhno) && authData.get("code").equals(inputAuthNo)) {
			return true;
		}
		else {
			return false;
		}
	}
}
