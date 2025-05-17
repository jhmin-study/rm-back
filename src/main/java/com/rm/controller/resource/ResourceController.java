package com.rm.controller.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rm.dto.resource.ResourceDTO;
import com.rm.service.resource.ResourceService;

@RestController
public class ResourceController {
	ResourceService resourceService;
	
	public ResourceController(ResourceService resourceService){
		this.resourceService = resourceService;
	}

	@PostMapping("/api/resource/{workplaceId}")
	public String createResource(
			@RequestBody ResourceDTO dto,
			@PathVariable(name="workplaceId") Long workplaceId) {
		resourceService.createResource(dto, workplaceId);
		return "성공";
	}
	
	@GetMapping("api/resource/{workplaceId}")
	public List<ResourceDTO> getResourceList(@PathVariable(name="workplaceId") long workplaceId){
		return resourceService.getResourceList(workplaceId);
	}
}
