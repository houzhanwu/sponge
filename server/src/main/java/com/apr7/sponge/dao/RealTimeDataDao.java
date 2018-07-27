package com.apr7.sponge.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.RealTimeData;

@Mapper
public interface RealTimeDataDao {
	void saveRealTimeRtd(@Param("workshopId") Long workshopId, @Param("rtdData") String rtdData, @Param("dataProtocol") int dataProtocol, @Param("lmodify") Date lmodify);

	void saveRealTimeStatus(@Param("workshopId") Long workshopId, @Param("statusData") String statusData, @Param("dataProtocol") int dataProtocol, @Param("lmodify") Date lmodify);

	List<RealTimeData> listAllRealTimeData();
}
