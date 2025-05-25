package com.rm.redis;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.user.UserDTO;

@RestController
public class SmsVerificationController {

	SmsVerificationService smsVerificationService;

	public SmsVerificationController(SmsVerificationService smsVerificationService) {
		this.smsVerificationService = smsVerificationService;
	}
	
	@PostMapping("/api/user/send-code")
	public String sendVerificationCode(@RequestBody UserDTO dto) {
		
		smsVerificationService.sendVerificationCode(dto);
		
		return "인증코드가 발송되었습니다.";
	}
	
	
}
