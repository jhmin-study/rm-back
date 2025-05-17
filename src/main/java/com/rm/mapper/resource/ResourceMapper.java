package com.rm.mapper.resource;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rm.dto.resource.Resource;

@Mapper
public interface ResourceMapper {

	public void createResource(Resource rse);

	public List<Resource> getResourceList(long workplaceId);
}
