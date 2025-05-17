package com.rm.dto.user;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/* ************************************************************************** */
/* Class명  : UserDTO                                                         */
/* 개발자   : 김재용                                                          */
/* 개발일자 : 2025.05.10                                                      */
/* ************************************************************************** */
@Getter
@Setter
public class UserDTO {
	private String        userId;         // 사용자ID(Email)
	private String        password;       // 비밀번호
	private String        lastPassword;   // 마지막 비밀번호
	private String        userNm;         // 회원명
	private String        userPhno;       // 회원전화번호
	private Character     quitYn;         // 탈회여부
	private LocalDateTime quitDt;         // 탈회일시
	private LocalDateTime signupDt;       // 가입일시
	private LocalDateTime createDt;       // 최초등록일시
	private LocalDateTime updateDt;       // 최종수정일시
}
/* ------------------------------ End of Class ------------------------------ */
