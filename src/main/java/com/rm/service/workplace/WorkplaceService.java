package com.rm.service.workplace;

import org.springframework.stereotype.Service;

import com.rm.dto.workplace.Workplace;
import com.rm.dto.workplace.WorkplaceDTO;
import com.rm.mapper.workplace.WorkplaceMapper;

@Service
public class WorkplaceService {
	WorkplaceMapper workplaceMapper;
	
	public WorkplaceService(WorkplaceMapper workplaceMapper) {
		this.workplaceMapper = workplaceMapper;
	}
	
	public WorkplaceDTO selectWorkplaceById(Long workplaceId) {
		Workplace wp = workplaceMapper.selectWorkplaceById(workplaceId);
		
		if (wp == null) {
			return null;
		}
		
		WorkplaceDTO dto = new WorkplaceDTO();
		
		dto.setWorkplaceId(wp.getWorkplaceId());
		dto.setUserId(wp.getUserId());
		dto.setBusinessTypeNm(wp.getBusinessTypeNm());
		dto.setBusinessRegNo(wp.getBusinessRegNo());
		dto.setBusinessName(wp.getBusinessName());
		dto.setOwnerName(wp.getOwnerName());
		dto.setAddress(wp.getAddress());
		dto.setDetailAddress(wp.getDetailAddress());
		dto.setStatus(wp.getStatus());
		dto.setCreatedAt(wp.getCreatedAt());
		dto.setUpdatedAt(wp.getUpdatedAt());
		
		return dto;
		
	}
}
