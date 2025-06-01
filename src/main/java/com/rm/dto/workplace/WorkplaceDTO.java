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
	private String businessRegNo;
	private String businessName;
	private String ownerName;
	private String phoneNumber;
	private String address;
	private String detailAddress;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@Override
	public String toString() {
		String res = "";
		res += "workplaceId : " + workplaceId + "\n"; 
		res += "userId : " + userId + "\n"; 
		res += "businessTypeNm : " + businessTypeNm + "\n"; 
		res += "businessRegNo : " + businessRegNo + "\n"; 
		res += "businessName : " + businessName + "\n"; 
		res += "ownerName : " + ownerName + "\n"; 
		res += "phoneNumber : " + phoneNumber + "\n"; 
		res += "address : " + address + "\n"; 
		res += "detailAddress : " + detailAddress + "\n"; 
		res += "status : " + status + "\n"; 
		res += "createdAt : " + createdAt + "\n"; 
		res += "updatedAt : " + updatedAt + "\n"; 
		return  res;
	}
}
