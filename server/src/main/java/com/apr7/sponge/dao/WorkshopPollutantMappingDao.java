package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Pollutant;

@MapperScan
public interface WorkshopPollutantMappingDao {

	void addWorkshopPollutantMappings(@Param("workshopId") Long workshopId, @Param("pollutantIds") List<Long> pollutantIds);

	List<Pollutant> listPollutantByWorkshopIds(@Param("workshopIds") List<Long> workshopIds);

	void deletePollutantByWorkshopId(@Param("workshopId") Long workshopId, @Param("pollutantId") Long pollutantId);
}
