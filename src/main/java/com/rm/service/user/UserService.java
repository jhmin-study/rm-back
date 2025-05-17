package com.rm.service.user;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rm.dto.user.UserDTO;
import com.rm.dto.user.UserEntity;
import com.rm.mapper.user.UserMapper;

/* ************************************************************************** */
/* Class명 : UserService                                                      */
/* 개발자  : 김재용                                                           */
/* 개발일자 : 2025.05.10                                                      */
/* 기능설명 : 회원 관련 Service 클래스                                        */
/* ************************************************************************** */
@Service
public class UserService {
	BCryptPasswordEncoder encoder;
	UserMapper userMapper;
	
	public UserService(BCryptPasswordEncoder encoder, UserMapper userMapper) {
		this.encoder = encoder;
		this.userMapper = userMapper;
	}
	
	
	// =========================================================================
	// 메소드 기능 : ID 중복여부 검사
	// EndPoint : /api/user/checkId
	// =========================================================================
	public Boolean checkId(String userId) {
		// 방어로직 - userId가 null이면 return false
		if (userId == null || userId.isEmpty()) {
			return false;
		}
		
		// 전달받은 userId로 DB에서 조회하여 중복여부 확인
		Integer isExist = userMapper.checkId(userId);
		if (isExist != null && isExist > 0) {
			// 중복된 ID가 존재하면 false 리턴
			return false;
		} else {
			return true;
		}
	}	// End of checkId

	// =========================================================================
	// 메소드 기능 : 회원가입
	// EndPoint : /api/user/signup
	// =========================================================================
	public Boolean Signup(UserDTO userDTO) {
		// 방어로직 - userDTO가 null이면  return false
		if (userDTO == null) {
			return false;
		}
		
		// 전달받은 DTO객체를 Entity에 담는다.
		UserEntity userEntity = new UserEntity();
		
		/* ****************************************************************** */
		/* ***                          DATA SET                          *** */
		/* ****************************************************************** */
		// ID (Email)
		userEntity.setUserId(userDTO.getUserId());
		
		// Password -> 암호화 하여 DB에 저장
		userEntity.setPassword(encoder.encode(userDTO.getPassword()));
		
		// 마지막 비밀번호 -> Skip
		// 회원명
		userEntity.setUserNm(userDTO.getUserNm());
		
		// 회원전화번호
		userEntity.setUserPhno(userDTO.getUserPhno());
		
		// 탈회여부
		userEntity.setQuitYn('N');
		
		// 탈회일시
		// 가입일시
		userEntity.setSignupDt(userDTO.getSignupDt());
		
		// 최초등록일시
		userEntity.setCreateDt(LocalDateTime.now());
		
		// 최종수정일시
		userEntity.setUpdateDt(LocalDateTime.now());
		
		/* ****************************************************************** */
		/* ***                        rm_user INSER                       *** */
		/* ****************************************************************** */
		try {
			userMapper.signup(userEntity);
		} catch (Exception e) {
			// 예외 발생 로그 출력
			System.out.println("회원가입 중 오류 발생 : " + e.getMessage());
			return false;
		}
		
		return true;
	}

}   // End of Class
/* -------------------------- End of UserService ---------------------------- */
