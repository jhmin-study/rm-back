package com.rm.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// =========================================================================
	// 메소드 기능 : 회원가입
	// EndPoint : /api/user/signup
	// 리턴값   : 회원가입 성공 -> success
	//            회원가입 실패 -> fail
	// =========================================================================
	@PostMapping("/api/user/signup")
	public String Signup(@RequestBody UserDTO userDTO) {
		// 회원가입 처리
		Boolean res = userService.Signup(userDTO);
		
		if (res) {
			return "success";
		}
		else {
			return "fail";
		}
	}
	
	// =========================================================================
	// 메소드 기능 : 회원정보 조회
	// EndPoint : /api/user/userId
	// 파라미터 : PathVariable userId
	// =========================================================================
	@GetMapping("/api/user/{userId}")
	public UserDTO getUserInfo(@PathVariable (name="userId") String userId) {
		
		UserDTO dto = userService.getUserInfo(userId);
		
		return dto;
	}
	
	// =========================================================================
	// 메소드 기능 : 회원 사업장 수 조회
	// EndPoint : /api/user/wkspCnt
	// 파라미터 : PathVariable userId
	// =========================================================================
	@GetMapping("/api/wkspCnt/{userId}")
	public Integer getWkspCnt(@PathVariable(name="userId") String userId) {
		
		Integer wkspCnt = userService.getWkspCnt(userId);
		System.out.println("wkspCnt : " + wkspCnt);
		return wkspCnt;
	}
	
	
}
/* ------------------------- End of UserController -------------------------- */

