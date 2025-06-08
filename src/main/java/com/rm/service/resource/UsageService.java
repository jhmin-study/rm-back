package com.rm.service.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rm.dto.resource.ResourceDTO;
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

	public boolean updateUsageInfo(UsageDTO dto, Long usageId) {
		Usage usage = new Usage();
		usage.setResourceUserName(dto.getResourceUserName());
		usage.setResourceUserPhone(dto.getResourceUserPhone());
		usage.setResourceUserEmail(dto.getResourceUserEmail());
		usage.setResourceUserNote(dto.getResourceUserNote());
		usage.setUsageStatus(dto.getUsageStatus());
		usage.setUsageSt(dto.getUsageSt());
		usage.setUsageEd(dto.getUsageEd());
		usage.setUsageId(usageId);
		usageMapper.updateUsageInfo(usage);
		return true;
	}

	public List<UsageDTO> getFutureUsageInfo(Long resourceId) {
		List<Usage> futureUsageList = usageMapper.getFutureUsageInfo(resourceId);
		List<UsageDTO> fulist = new ArrayList<>();
		for(Usage usage : futureUsageList) {
			UsageDTO dto = new UsageDTO();
			dto.setResourceId(usage.getResourceId());
			dto.setUsageId(usage.getUsageId());
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

	public boolean deleteUsage(Long usageId) {
		usageMapper.deleteUsage(usageId);
		return true;
		
	}
	
	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정 실행
	public void autoUpdateUsageStatus() {
	    usageMapper.updateUsageStatus();
	}

	public List<LocalDate> getDisabledDateExceptThis(Long resourceId) {
		return usageMapper.getDisabledDateExceptThis(resourceId);
	}
	

	public List<UsageDTO> getUsageHistory(Long resourceId) {
		List<Usage> usageList = usageMapper.getUsageHistory(resourceId);
		List<UsageDTO> usageDTO = new ArrayList<>();
		for(Usage usage : usageList) {
			UsageDTO dto = new UsageDTO();
			dto.setResourceId(usage.getResourceId());
			dto.setUsageId(usage.getUsageId());
			dto.setResourceUserName(usage.getResourceUserName());
			dto.setResourceUserPhone(usage.getResourceUserPhone());
			dto.setResourceUserEmail(usage.getResourceUserEmail());
			dto.setResourceUserNote(usage.getResourceUserNote());
			dto.setUsageSt(usage.getUsageSt());
			dto.setUsageEd(usage.getUsageEd());
			
			usageDTO.add(dto);
		}
		return usageDTO;
	}
}
