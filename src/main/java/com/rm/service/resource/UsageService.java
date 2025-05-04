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

	public boolean createUsage(UsageDTO dto) {
		Usage usage = new Usage();
		usage.setResourceUserName(dto.getResourceUserName());
		usage.setResourceUserPhone(dto.getResourceUserPhone());
		usage.setResourceUserEmail(dto.getResourceUserEmail());
		usage.setResourceUserNote(dto.getResourceUserNote());
		usage.setUsageStatus(dto.getUsageStatus());
		usage.setUsageSt(dto.getUsageSt());
		usage.setUsageEd(dto.getUsageEd());
		usageMapper.createUsage(usage);
		return true;
	}
}
