package com.rm.dto.resource;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsageDTO {
	private long usageId;
	private long resourceId;
	private String usageStatus;
	private String resourceUserName;
	private String resourceUserPhone;
	private String resourceUserEmail;
	private String resourceUserNote;
	private LocalDate usageSt;
	private LocalDate usageEd;
}
