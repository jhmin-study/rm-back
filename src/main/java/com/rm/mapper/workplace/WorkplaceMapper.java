package com.rm.mapper.workplace;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rm.dto.workplace.Workplace;
import com.rm.dto.workplace.WorkplaceDTO;

@Mapper
public interface WorkplaceMapper {
	// 사업장 조회
	public Workplace selectWorkplaceById(Long workplaceId);
	// 사업자별 등록된 사업장 갯수 조회 (사업장 갯수 제한 필요)
	public int countWorkplaces(String userId);
	// 사업장 등록(생성)
	public void createWorkplace(Workplace workplace);
	// 사업장 삭제 
	public void deleteWorkplace(Long workplaceId);
	// 사업장 수정
	public void updateWorkplaceById(WorkplaceDTO dto);
	// 사업장의 자원 삭제 (FK라서 먼저 삭제해야함)
	public void deleteWorkplaceResource(Long workplaceId);
	//전체 사업장 리스트 조회
	public List<Workplace> selectWorkplacesByUserId(String userId);
	public boolean isBusinessRegNoExists(String businessRegNo);
}
