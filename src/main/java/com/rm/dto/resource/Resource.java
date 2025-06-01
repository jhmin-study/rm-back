package com.rm.dto.resource;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource {
	private long resourceId;
	private long workplaceId;
	private String resourceName;
	private String place;
	private LocalDate createdAt;
	private LocalDate updatedAt;
    private Usage resourceUsage;
    
    @Override
    public String toString() {
    	String res = "{";
    	res += "resourceId : " + resourceId + "\n";
    	res += "workplaceId : " + workplaceId + "\n";
    	res += "resourceName : " + resourceName + "\n";
    	res += "place : " + place + "\n";
    	res += "createdAt : " + createdAt + "\n";
    	res += "updatedAt : " + updatedAt + "\n";
    	res += "resourceUsage : " + resourceUsage + "\n";
    	res += "}\n";
    	return res;
    }
}
