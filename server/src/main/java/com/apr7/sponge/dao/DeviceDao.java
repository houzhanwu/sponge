package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.Device;
import com.apr7.sponge.model.Workshop;

@Mapper
public interface DeviceDao {
	void addDevice(Device device);

	void deleteDeviceByWorkshopId(@Param("workshopId") Long workshopId);

	void updateDevice(Device device);

	List<Device> listActiveDevice();

	Device getDeviceByWorkshopId(@Param("workshopId") Long workshopId);

	List<Device> listDeviceByWorkshopIds(@Param("workshopIds") List<Long> workshopIds);

	String getMnByWorkshopId(@Param("workshopId") Long workshopId);

	Long getWorkshopIdByMn(@Param("mn") String mn);

	Workshop getWorkshopByMn(@Param("mn") String mn);
}
