package com.rm.dto.resource;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource {
	private long resourceId;
	private long workplaceId;
	private String resourceName;
	private String place;
	private String usageStatus;
	private String resourceUserName;
	private String resourceUserPhone;
	private LocalDate usageSt;
	private LocalDate usageEd;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
