package com.rm.controller.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.resource.UsageDTO;
import com.rm.service.resource.UsageService;

@RestController
public class UsageController {
	
	UsageService usageService;
	
	public UsageController(UsageService usageService) {
		this.usageService = usageService;
	}
	
	@PostMapping("/api/usage/{resourceId}")
	public String createUsage(
			@RequestBody UsageDTO dto,
			@PathVariable(name="resourceId") Long resourceId) {
		boolean res = usageService.createUsage(dto, resourceId);
		if(res) {			
			return "성공";
		}else {
			return "실패";
		}
	}
	
	@GetMapping("/api/usage/{resourceId}")
	public UsageDTO getUsageInfo(@PathVariable(name="resourceId") Long resourceId) {
		return usageService.getUsageInfo(resourceId);
	}
	
	@GetMapping("api/futureUsage/{resourceId}")
	public List<UsageDTO> getFutureUsageInfo(@PathVariable(name="resourceId") Long resourceId){
		return usageService.getFutureUsageInfo(resourceId);
	}
	
	@PutMapping("/api/usage/{resourceId}")
	public String updateUsageInfo(
			@RequestBody UsageDTO dto,
			@PathVariable(name="resourceId") Long resourceId) {
		if(usageService.updateUsageInfo(dto, resourceId)) {			
			return "성공";
		}else {
			return "실패";
		}
	}
}
