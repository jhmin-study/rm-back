package com.rm.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource {
	private long resourceId;
	private String place;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
