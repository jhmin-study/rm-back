package com.rm.dto.resource;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsageDTO {
	private long usageId;
	private String usageStatus;
	private String resourceUser;
	private LocalDate usageSt;
	private LocalDate usageEd;
}
