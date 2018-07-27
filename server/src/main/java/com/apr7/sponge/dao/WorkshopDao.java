package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.Workshop;

@Mapper
public interface WorkshopDao {
	void addWorkshop(Workshop workshop);

	void deleteWorkshop(@Param("workshopId") Long workshopId);

	void updateWorkshop(Workshop workshop);

	Workshop getWorkshop(@Param("workshopId") Long workshopId);

	List<Workshop> listWorkshopNameByCompanyId(@Param("companyId") Long companyId);

	List<Workshop> listWorkshopByCompanyId(@Param("companyId") Long companyId);

	List<Long> listWorkshopIdsByCompanyId(@Param("companyId") Long companyId);
}
