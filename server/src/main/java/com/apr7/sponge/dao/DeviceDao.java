package com.apr7.sponge.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Device;

@MapperScan
public interface DeviceDao {
	void addDevice(Device device);

	void deleteDeviceByWorkshopId(@Param("workshopId") Long workshopId);

	void updateDevice(Device device);

	String getMnByWorkshopId(@Param("workshopId") Long workshopId);
}
