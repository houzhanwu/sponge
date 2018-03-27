package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.WorkshopPollutantMapping;

@MapperScan
public interface WorkshopPollutantMappingDao {

	void saveWorkshopPollutantMappings(@Param("workshopId") Long workshopId, @Param("pollutantIds") List<Long> pollutantIds);

	void deletePollutantByWorkshopId(@Param("workshopId") Long workshopId, @Param("pollutantId") Long pollutantId);

	void deletePollutant(@Param("pollutantId") Long pollutantId);

	void updateStatusByWorkshopId(@Param("workshopId") Long workshopId, @Param("status") Integer status, @Param("excludePollutantIds") List<Long> excludePollutantIds);

	List<WorkshopPollutantMapping> listWorkshopPollutantMappingByWorkshopIds(@Param("workshopIds") List<Long> workshopIds);
}
