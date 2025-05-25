package com.rm.controller.workplace;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.workplace.Workplace;
import com.rm.dto.workplace.WorkplaceDTO;
import com.rm.service.workplace.WorkplaceService;

@RestController
public class WorkplaceController {
	WorkplaceService workplaceService;
	
	public WorkplaceController(WorkplaceService workplaceService) {
		this.workplaceService = workplaceService;
	}
	
	// 사업장 정보 조회
	@GetMapping("/api/workplace/{workplaceId}")
	public WorkplaceDTO getWorkplaceById(
			@PathVariable(name = "workplaceId") Long workplaceId) {
		return workplaceService.selectWorkplaceById(workplaceId);
	}
	
	// 사업장 정보 등록
	@PostMapping("/api/workplace")
	public Long createWorkplace(@RequestBody WorkplaceDTO dto) {		
		System.out.println(dto);
		Long res = workplaceService.createWorkplace(dto);
		System.out.println(res + "번 사업장이 생성되었음");
		return res;
	}
	
	// 사업장 삭제
	@DeleteMapping("/api/workplace/{workplaceId}")
	public String deleteWorkplace(@PathVariable(value="workplaceId") Long workplaceId) {
		if (workplaceService.deleteWorkplace(workplaceId)) {
			return "성공";
		}
		return "실패";
	}
	
	// 사업장 수정
	@PutMapping("/api/workplace/{workplaceId}")
	public ResponseEntity<String> updateWorkplaceById(@PathVariable(name="workplaceId") long workplaceId, @RequestBody WorkplaceDTO dto) {
		WorkplaceDTO workplace = workplaceService.selectWorkplaceById(workplaceId);
		
		if (workplace == null) {
			return ResponseEntity.status(404).body(null);
		}
		
		// workplace에서는 수정 전 값이 들어있다
		if (dto.getBusinessTypeNm() != null) {
			workplace.setBusinessTypeNm(dto.getBusinessTypeNm());
		}
		if (dto.getBusinessRegNo() != null) {
			workplace.setBusinessRegNo(dto.getBusinessRegNo());
		}
		if (dto.getBusinessName() != null) {
			workplace.setBusinessName(dto.getBusinessName());
		}
		if (dto.getOwnerName() != null) {
			workplace.setOwnerName(dto.getOwnerName());
		}
		if (dto.getPhoneNumber() != null) {
			workplace.setPhoneNumber(dto.getPhoneNumber());
		}
		if (dto.getAddress() != null) {
			workplace.setAddress(dto.getAddress());
		}
		if (dto.getDetailAddress() != null) {
			workplace.setDetailAddress(dto.getDetailAddress());
		}
		if (dto.getStatus() != null) {
			workplace.setStatus(dto.getStatus());
		}

		workplaceService.updateWorkplaceById(workplace);
		System.out.println(dto);
			
		return ResponseEntity.status(200).body("수정 성공!");
	}
	
	// 전체 사업장 조회
	@GetMapping("/api/workplaces")
	public List<WorkplaceDTO> getWorkplaces() {
	    return workplaceService.selectWorkplacesByUserId();
	}

}
