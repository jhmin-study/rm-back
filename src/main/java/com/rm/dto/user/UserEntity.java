package com.rm.dto.user;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/* ************************************************************************** */
/* Class명  : UserEntity                                                      */
/* 개발자   : 김재용                                                          */
/* 개발일자 : 2025.05.10                                                      */
/* ************************************************************************** */
@Getter
@Setter
public class UserEntity {
	String        userId;         // 사용자ID(Email)
	String        password;       // 비밀번호
	String        lastPassword;   // 마지막 비밀번호
	String        userNm;         // 회원명
	String        userPhno;       // 회원전화번호
	Character     quitYn;         // 탈회여부
	LocalDateTime quitDt;         // 탈회일시
	LocalDateTime signupDt;       // 가입일시
	LocalDateTime createDt;       // 최초등록일시
	LocalDateTime updateDt;       // 최종수정일시
}   // End of UserEntity
/* ------------------------------ End of Class ------------------------------ */
