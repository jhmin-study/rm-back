package com.rm.service.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	public List<UsageDTO> getFutureUsageInfo(Long resourceId) {
		List<Usage> futureUsageList = usageMapper.getFutureUsageInfo(resourceId);
		List<UsageDTO> fulist = new ArrayList<>();
		for(Usage usage : futureUsageList) {
			UsageDTO dto = new UsageDTO();
			dto.setResourceUserName(usage.getResourceUserName());
			dto.setResourceUserPhone(usage.getResourceUserPhone());
			dto.setResourceUserEmail(usage.getResourceUserEmail());
			dto.setResourceUserNote(usage.getResourceUserNote());
			dto.setUsageSt(usage.getUsageSt());
			dto.setUsageEd(usage.getUsageEd());
			
			fulist.add(dto);
		}
		return fulist;
	}
	
	public List<LocalDate> getDisabledDate(Long resourceId){
		return usageMapper.getDisabledDate(resourceId);
	}

	public void deleteUsage(Long usageId) {
		usageMapper.deleteUsage(usageId);
		
	}
}
