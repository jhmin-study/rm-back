package com.rm.redis;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.user.UserDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SmsVerificationController {

	SmsVerificationService smsVerificationService;

	public SmsVerificationController(SmsVerificationService smsVerificationService) {
		this.smsVerificationService = smsVerificationService;
	}
	
	/**
	 * 인증코드 요청 API
	 * @param USERDTO
	 * @return 성공 시 "success"
	 */
	@PostMapping("/api/user/send-code")
	public String sendVerificationCode(@RequestBody UserDTO dto) {
		
		log.info("인증요청 수신됨. dto={}", dto);
		
		smsVerificationService.sendVerificationCode(dto);
		
		return "success";
	}
	
	/**
	 * 인증코드 확인 API
	 * @param 
	 */
	@PostMapping("/api/user/checkauthno")
	public String checkAuthNo(@RequestBody UserDTO dto) {
		
		if (smsVerificationService.checkAuthNo(dto)) {
			return "success";
		} else {
			return "fail";
		}
		
	}
}
