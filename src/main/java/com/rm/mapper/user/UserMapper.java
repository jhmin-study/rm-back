package com.rm.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.rm.dto.user.UserEntity;

/* ************************************************************************** */
/* Class명 : UserMapper                                                       */
/* 개발자  : 김재용                                                           */
/* 개발일자 : 2025.05.10                                                      */
/* 기능설명 : 회원 관련 Mapper 클래스                                         */
/* ************************************************************************** */
@Mapper
public interface UserMapper {
	
	// ID 중복여부 확인
	public Integer checkId(String userId);

	// 회원가입
	public void signup(UserEntity userEntity);
	
	// ID로 사용자 정보 조회
	public UserEntity getUserById(String username);
	
	// ID로 사업장 수 조회
	public Integer getWkspCnt(String userId);
	
}
/* --------------------------- End of UserMapper ---------------------------- */
