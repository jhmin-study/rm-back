package com.rm.controller.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.resource.UsageDTO;
import com.rm.service.resource.UsageService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UsageController {
	
	UsageService usageService;
	
	public UsageController(UsageService usageService) {
		this.usageService = usageService;
	}
	
	@PostMapping("/api/resource/{resourceId}/usage")
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
	
	@GetMapping("/api/resource/{resourceId}/futureUsage")
	public List<UsageDTO> getFutureUsageInfo(@PathVariable(name="resourceId") Long resourceId){
		return usageService.getFutureUsageInfo(resourceId);
	}
	
	@PutMapping("/api/usage/{usageId}")
	public String updateUsageInfo(
			@RequestBody UsageDTO dto,
			@PathVariable(name="usageId") Long usageId) {
		if(usageService.updateUsageInfo(dto, usageId)) {			
			return "성공";
		}else {
			return "실패";
		}
	}
	
	@GetMapping("/api/resource/{resourceId}/usedDate")
	public List<LocalDate> getDisabledDate(@PathVariable(name="resourceId") Long resourceId){
		return usageService.getDisabledDate(resourceId);
	}

	@GetMapping("/api/resource/{resourceId}/another/usedDate")
	public List<LocalDate> getDisabledDateExceptThis(@PathVariable(name="resourceId") Long resourceId){
		return usageService.getDisabledDateExceptThis(resourceId);
	}
	
	@DeleteMapping("/api/usage/{usageId}")
	public String deleteUsage(@PathVariable(name="usageId") Long usageId) {
		if(usageService.deleteUsage(usageId)) {
			return "성공";
		}else {
			return "실패";
		}
	}
	
	@GetMapping("/api/resource/{resourceId}/usage/history")
	public List<UsageDTO> getUsageHistory(@PathVariable(name="resourceId") Long resourceId) {
		return usageService.getUsageHistory(resourceId);
	}
}
