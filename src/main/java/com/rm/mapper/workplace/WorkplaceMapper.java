package com.rm.mapper.workplace;

import org.apache.ibatis.annotations.Mapper;

import com.rm.dto.workplace.Workplace;

@Mapper
public interface WorkplaceMapper {
	public Workplace selectWorkplaceById(Long workplaceId);
}
