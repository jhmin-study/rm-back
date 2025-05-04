package com.rm.dto.workplace;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkplaceDTO {
	private Long workplaceId;
	private String userId;
	private String businessTypeNm;
	private Long businessRegNo;
	private String businessName;
	private String ownerName;
	private String phoneNumber;
	private String address;
	private String detailAddress;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
