package com.rm.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usage {
	private long usageId;
	private String usageStatus;
	private String resourceUser;
	private LocalDate usageSt;
	private LocalDate usageEd;
}
