package com.rm.mapper.user;

import org.apache.ibatis.annotations.Mapper;

/* ************************************************************************** */
/* Class명 : UserMapper                                                       */
/* 개발자  : 김재용                                                           */
/* 개발일자 : 2025.05.10                                                      */
/* 기능설명 : 회원 관련 Mapper 클래스                                         */
/* ************************************************************************** */
@Mapper
public interface UserMapper {

	public Integer checkId(String userId);

}
/* --------------------------- End of UserMapper ---------------------------- */
