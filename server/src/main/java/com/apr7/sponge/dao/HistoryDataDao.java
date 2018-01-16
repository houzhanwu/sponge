package com.apr7.sponge.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.HistoryData;

@MapperScan
public interface HistoryDataDao {
	List<HistoryData> listHistoryDataByCompanyId(@Param("companyId") Long companyId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}