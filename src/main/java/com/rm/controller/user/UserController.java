package com.rm.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.user.UserDTO;
import com.rm.service.user.UserService;

/* ************************************************************************** */
/* Class명 : UserController                                                   */
/* 개발자  : 김재용                                                           */
/* 개발일자 : 2025.05.10                                                      */
/* 기능설명 : 회원 관련 Controller 클래스                                     */
/* ************************************************************************** */
@RestController
public class UserController {
	
	UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// =========================================================================
	// 메소드 기능 : ID 중복여부 검사
	// EndPoint : /api/user/checkId
	// 리턴값   : 계정 기존재 -> false
	//            계정 미존재 -> true
	// =========================================================================
	@PostMapping("/api/user/checkId")
	public Boolean checkId(@RequestBody UserDTO userDTO) {
		return userService.checkId(userDTO.getUserId());
	}
	
	
}
/* ------------------------- End of UserController -------------------------- */

