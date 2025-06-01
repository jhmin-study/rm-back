package com.rm.service.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rm.dto.resource.Resource;
import com.rm.dto.resource.ResourceDTO;
import com.rm.dto.resource.Usage;
import com.rm.dto.resource.UsageDTO;
import com.rm.mapper.resource.ResourceMapper;

@Service
public class ResourceService {
	ResourceMapper remap;
	
    public ResourceService(ResourceMapper remap) {
        this.remap = remap;
    }

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
			
			Usage checkUsageNull = resource.getResourceUsage();
			if(checkUsageNull != null) {				
				UsageDTO usagedto = new UsageDTO();
				usagedto.setUsageStatus(resource.getResourceUsage().getUsageStatus());
				usagedto.setResourceUserName(resource.getResourceUsage().getResourceUserName());
				usagedto.setResourceUserPhone(resource.getResourceUsage().getResourceUserPhone());
				usagedto.setResourceUserEmail(resource.getResourceUsage().getResourceUserEmail());
				usagedto.setResourceUserNote(resource.getResourceUsage().getResourceUserNote());
				usagedto.setUsageSt(resource.getResourceUsage().getUsageSt());
				usagedto.setUsageEd(resource.getResourceUsage().getUsageEd());
				dto.setResourceUsage(usagedto);
			}
			
			dtoList.add(dto);
		}
		return dtoList;
	}

	public void updateResource(long resourceId, ResourceDTO dto) {
		Resource rs = new Resource();
		rs.setResourceId(resourceId);		
		rs.setResourceName(dto.getResourceName());
		rs.setPlace(dto.getPlace());
		remap.updateResource(rs);
	}

	public ResourceDTO getResourceInfo(long resourceId) {
		Resource res = remap.getResourceInfo(resourceId);
		ResourceDTO dto = new ResourceDTO();
		dto.setResourceName(res.getResourceName());
		dto.setPlace(res.getPlace());
		dto.setWorkplaceId(res.getWorkplaceId());
		
		Usage usage = res.getResourceUsage();
		if(usage != null) {
			UsageDTO usdto = new UsageDTO();
			usdto.setUsageId(res.getResourceUsage().getUsageId());
			usdto.setUsageStatus(res.getResourceUsage().getUsageStatus());
			usdto.setResourceUserName(res.getResourceUsage().getResourceUserName());
			usdto.setResourceUserPhone(res.getResourceUsage().getResourceUserPhone());
			usdto.setResourceUserEmail(res.getResourceUsage().getResourceUserEmail());
			usdto.setResourceUserNote(res.getResourceUsage().getResourceUserNote());
			usdto.setUsageSt(res.getResourceUsage().getUsageSt());
			usdto.setUsageEd(res.getResourceUsage().getUsageEd());
			dto.setResourceUsage(usdto);
		}
		
		return dto;
		
	}

	public ResourceDTO getResourceNamePlace(long resourceId) {
		Resource rs = remap.getResourceNamePlace(resourceId);
		ResourceDTO dtodto = new ResourceDTO();
		dtodto.setResourceName(rs.getResourceName());
		dtodto.setPlace(rs.getPlace());
		return dtodto;
	}

}
