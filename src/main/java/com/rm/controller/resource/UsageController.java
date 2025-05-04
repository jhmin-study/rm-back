package com.rm.controller.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.resource.UsageDTO;
import com.rm.service.resource.UsageService;

@RestController
public class UsageController {
	
	UsageService usageService;
	
	@PostMapping("/api/usage/{resourceId}")
	public String createUsage(
			@RequestBody UsageDTO dto,
			@PathVariable(name="resourceId") Integer resourceId) {
		if(!usageService.createUsage(dto)) {			
			return "실패";
		}else {
			return "성공";
		}
	}
}
