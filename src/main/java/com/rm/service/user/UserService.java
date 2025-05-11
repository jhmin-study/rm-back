package com.rm.service.user;

import org.springframework.stereotype.Service;

import com.rm.mapper.user.UserMapper;

/* ************************************************************************** */
/* Class명 : UserService                                                      */
/* 개발자  : 김재용                                                           */
/* 개발일자 : 2025.05.10                                                      */
/* 기능설명 : 회원 관련 Service 클래스                                        */
/* ************************************************************************** */
@Service
public class UserService {

	UserMapper userMapper;
	
	public UserService(UserMapper userMapper) {
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

}
/* -------------------------- End of UserService ---------------------------- */
