package com.rm.service.workplace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rm.dto.workplace.Workplace;
import com.rm.dto.workplace.WorkplaceDTO;
import com.rm.exception.DuplicateBusinessRegNoException;
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
		dto.setPhoneNumber(wp.getPhoneNumber());
		dto.setAddress(wp.getAddress());
		dto.setDetailAddress(wp.getDetailAddress());
		dto.setStatus(wp.getStatus());
		dto.setCreatedAt(wp.getCreatedAt());
		dto.setUpdatedAt(wp.getUpdatedAt());
		
		return dto;
		
	}
	
	@Transactional
	public Long createWorkplace(WorkplaceDTO dto) {
		if (workplaceMapper.isBusinessRegNoExists(dto.getBusinessRegNo())) {
	        throw new DuplicateBusinessRegNoException("이미 존재하는 사업자 등록번호입니다.");
	    }
		
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		Workplace workplace = new Workplace();
		
		workplace.setUserId(userId);
		workplace.setBusinessTypeNm(dto.getBusinessTypeNm());
		workplace.setBusinessRegNo(dto.getBusinessRegNo());
		workplace.setBusinessName(dto.getBusinessName());
		workplace.setOwnerName(dto.getOwnerName());
		workplace.setPhoneNumber(dto.getPhoneNumber());
		workplace.setAddress(dto.getAddress());
		workplace.setDetailAddress(dto.getDetailAddress());
		workplace.setStatus(dto.getStatus());
		
		LocalDateTime dt = LocalDateTime.now();
		workplace.setCreatedAt(dt);
		workplace.setUpdatedAt(dt);
		
		workplaceMapper.createWorkplace(workplace);
		return workplace.getWorkplaceId();
	}
	
	public Boolean deleteWorkplace(Long workplaceId) {
		Workplace wp = workplaceMapper.selectWorkplaceById(workplaceId);
		
		if(wp == null) {
			return false;
		}
		
		// DB에서 workplace를 참고하고 있는 테이블 (rm_resource) 에서 먼저 삭제한 다음
		workplaceMapper.deleteWorkplaceResource(workplaceId);
		// workplace를 삭제한다.
		workplaceMapper.deleteWorkplace(workplaceId);
		return true;
	}
	
	public void updateWorkplaceById(WorkplaceDTO dto) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUserId(userId);
		System.out.println(dto);
		workplaceMapper.updateWorkplaceById(dto);
	}
	
	public List<WorkplaceDTO> selectWorkplacesByUserId() {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
//		System.out.println(userId);
	    List<Workplace> workplaces = workplaceMapper.selectWorkplacesByUserId(userId);
	    List<WorkplaceDTO> dtoList = new ArrayList<>();

	    for (Workplace wp : workplaces) {
	        WorkplaceDTO dto = new WorkplaceDTO();
	        dto.setWorkplaceId(wp.getWorkplaceId());
	        dto.setUserId(wp.getUserId());
	        dto.setBusinessTypeNm(wp.getBusinessTypeNm());
	        dto.setBusinessRegNo(wp.getBusinessRegNo());
	        dto.setBusinessName(wp.getBusinessName());
	        dto.setOwnerName(wp.getOwnerName());
	        dto.setPhoneNumber(wp.getPhoneNumber());
	        dto.setAddress(wp.getAddress());
	        dto.setDetailAddress(wp.getDetailAddress());
	        dto.setStatus(wp.getStatus());
	        dto.setCreatedAt(wp.getCreatedAt());
	        dto.setUpdatedAt(wp.getUpdatedAt());
	        dtoList.add(dto);
	    }

	    return dtoList;
	}

}
