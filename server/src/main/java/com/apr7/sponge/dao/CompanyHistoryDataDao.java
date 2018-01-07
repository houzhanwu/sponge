package com.apr7.sponge.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.CompanyHistoryData;

@MapperScan
public interface CompanyHistoryDataDao {
	List<CompanyHistoryData> listCompanyHistoryData(@Param("companyId") Long companyId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
