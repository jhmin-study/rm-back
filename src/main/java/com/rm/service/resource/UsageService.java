package com.rm.service.resource;

import org.springframework.stereotype.Service;

import com.rm.dto.resource.Usage;
import com.rm.dto.resource.UsageDTO;
import com.rm.mapper.resource.UsageMapper;

@Service
public class UsageService {
	UsageMapper usageMapper;
	
	public UsageService(UsageMapper usageMapper) {
		this.usageMapper = usageMapper;
	}

	public boolean createUsage(UsageDTO dto, Long resourceId) {
		Usage usage = new Usage();
		usage.setResourceUserName(dto.getResourceUserName());
		usage.setResourceUserPhone(dto.getResourceUserPhone());
		usage.setResourceUserEmail(dto.getResourceUserEmail());
		usage.setResourceUserNote(dto.getResourceUserNote());
		usage.setUsageStatus(dto.getUsageStatus());
		usage.setUsageSt(dto.getUsageSt());
		usage.setUsageEd(dto.getUsageEd());
		usage.setResourceId(resourceId);
		usageMapper.createUsage(usage);
		return true;
	}

	public UsageDTO getUsageInfo(Long resourceId) {
		Usage usage = usageMapper.getUsageInfo(resourceId);
		
		UsageDTO dto = new UsageDTO();
		dto.setUsageStatus(usage.getUsageStatus());
		dto.setResourceUserName(usage.getResourceUserName());
		dto.setResourceUserPhone(usage.getResourceUserPhone());
		dto.setResourceUserEmail(usage.getResourceUserEmail());
		dto.setResourceUserNote(usage.getResourceUserNote());
		dto.setUsageSt(usage.getUsageSt());
		dto.setUsageEd(usage.getUsageEd());
		
		return dto;
		
	}

	public boolean updateUsageInfo(UsageDTO dto, Long resourceId) {
		Usage usage = new Usage();
		usage.setResourceUserName(dto.getResourceUserName());
		usage.setResourceUserPhone(dto.getResourceUserPhone());
		usage.setResourceUserEmail(dto.getResourceUserEmail());
		usage.setResourceUserNote(dto.getResourceUserNote());
		usage.setUsageStatus(dto.getUsageStatus());
		usage.setUsageSt(dto.getUsageSt());
		usage.setUsageEd(dto.getUsageEd());
		usage.setResourceId(resourceId);
		usageMapper.updateUsageInfo(usage);
		return true;
	}
}
