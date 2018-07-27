package com.apr7.sponge.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.HistoryData;

@Mapper
public interface HistoryDataDao {

	void addHistoryData(@Param("year") int year, @Param("historyData") HistoryData historyData);

	List<HistoryData> listHistoryDataByCompanyId(@Param("year") int year, @Param("companyId") Long companyId, @Param("workshopId") Long workshopId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

	boolean existsTable(@Param("year") int year);

	void createHistoryTable(@Param("year") int year);
}
