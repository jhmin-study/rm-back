package com.rm.dto.resource;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceDTO {
	private long resourceId;
	private long workplaceId;
	private String place;
	private String resourceName;
	private Usage resourceUsage;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
