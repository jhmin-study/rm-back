package com.rm.redis;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RandomAuthUtil {
	private static final String CHAR_DIGITS = "0123456789";
	
	private static final String ALL_CHARS = CHAR_DIGITS;
	
	private static final int keyLength = 6;	// 생성 길이
	
	public String generateRandomKey() {
		StringBuilder sb = new StringBuilder();
		
		Random random = new SecureRandom();
		for (int i = 0; i < keyLength; i++) {
			int randomIndex = random.nextInt(ALL_CHARS.length());
			sb.append(ALL_CHARS.charAt(randomIndex));
		}
		log.info("Generated Random Key : {}", sb.toString());
		
		return sb.toString();
	}
}
/* ------------------------- End of RandomAuthUtil -------------------------- */

