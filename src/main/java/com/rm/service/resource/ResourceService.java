package com.rm.service.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rm.dto.resource.Resource;
import com.rm.dto.resource.ResourceDTO;
import com.rm.mapper.resource.ResourceMapper;

@Service
public class ResourceService {
	ResourceMapper remap;

	public void createResource(ResourceDTO dto, Long workplaceId) {
		Resource rse = new Resource();
		rse.setResourceName(dto.getResourceName());
		rse.setPlace(dto.getPlace());
		rse.setWorkplaceId(workplaceId);
		remap.createResource(rse);
	}

	public List<ResourceDTO> getResourceList(long workplaceId) {
		List<Resource> resourceList = remap.getResourceList(workplaceId);
		List<ResourceDTO> dtoList = new ArrayList<>();
		for (Resource resource : resourceList) {
			ResourceDTO dto = new ResourceDTO();
			dto.setResourceId(resource.getResourceId());
			dto.setResourceName(resource.getResourceName());
			dto.setPlace(resource.getPlace());
			dto.setResourceUsage(resource.getResourceUsage());
			dtoList.add(dto);
		}
		return dtoList;
	}

}
