package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Device;

@MapperScan
public interface DeviceDao {
	List<Device> listDeviceByCompany(@Param("companyId") Long companyId, @Param("start") int start,
			@Param("max") int max);

	int countDeviceByCompany(@Param("companyId") Long companyId);
}
