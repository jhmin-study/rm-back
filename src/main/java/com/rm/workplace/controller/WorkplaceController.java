package com.rm.workplace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.WorkplaceDTO;
import com.rm.service.WorkplaceService;

@RestController
public class WorkplaceController {
	WorkplaceService workplaceService;
	
	public WorkplaceController(WorkplaceService workplaceService) {
		this.workplaceService = workplaceService;
	}
	
	// 게시물 1건 조회
	@GetMapping("/api/workplace/{workplaceId}")
	public WorkplaceDTO getWorkplaceById(
			@PathVariable(name = "workplaceId") Long workplaceId) {
		
		return workplaceService.selectWorkplaceById(workplaceId);
	}
}
