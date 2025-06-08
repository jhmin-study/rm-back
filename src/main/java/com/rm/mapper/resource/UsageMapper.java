package com.rm.mapper.resource;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rm.dto.resource.Resource;
import com.rm.dto.resource.Usage;

@Mapper
public interface UsageMapper {

	public void createUsage(Usage usage);

	public void updateUsageInfo(Usage usage);

	public List<Usage> getFutureUsageInfo(Long resourceId);
	
	public List<LocalDate> getDisabledDate(Long resourceId);

	public List<LocalDate> getDisabledDateExceptThis(Long resourceId);

	public void deleteUsage(Long usageId);

	public void updateUsageStatus();
	
	public List<Usage> getUsageHistory(Long resourceId);

}
