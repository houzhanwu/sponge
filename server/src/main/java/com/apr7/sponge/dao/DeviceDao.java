package com.apr7.sponge.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Device;

@MapperScan
public interface DeviceDao {
	List<Device> listDeviceByCompany(Long companyId, int start, int max);

	int countDeviceByCompany(Long companyId);
}
