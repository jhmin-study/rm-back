package com.rm.mapper.resource;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rm.dto.resource.Usage;

@Mapper
public interface UsageMapper {

	public void createUsage(Usage usage);

	public Usage getUsageInfo(Long resourceId);

	public void updateUsageInfo(Usage usage);

	public List<Usage> getFutureUsageInfo(Long resourceId);

}
