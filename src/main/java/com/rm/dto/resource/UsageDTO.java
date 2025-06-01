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
	
	@Override
    public String toString() {
    	String res = "{";
    	res += "usageId : " + usageId + "\n";
    	res += "resourceId : " + resourceId + "\n";
    	res += "usageStatus : " + usageStatus + "\n";
    	res += "resourceUserName : " + resourceUserName + "\n";
    	res += "resourceUserPhone : " + resourceUserPhone + "\n";
    	res += "resourceUserEmail : " + resourceUserEmail + "\n";
    	res += "resourceUserNote : " + resourceUserNote + "\n";
    	res += "usageSt : " + usageSt + "\n";
    	res += "usageEd : " + usageEd + "\n";
    	res += "}\n";
    	return res;
    }
}
