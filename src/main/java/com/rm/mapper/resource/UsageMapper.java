package com.rm.mapper.resource;

import org.apache.ibatis.annotations.Mapper;

import com.rm.dto.resource.Usage;

@Mapper
public interface UsageMapper {

	public void createUsage(Usage usage);

}
