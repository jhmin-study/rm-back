package com.rm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.rm.dto.Workplace;

@Mapper
public interface WorkplaceMapper {
	public Workplace selectWorkplaceById(Long workplaceId);
}
