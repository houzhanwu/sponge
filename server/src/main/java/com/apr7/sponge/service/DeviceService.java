package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.DeviceDao;
import com.apr7.sponge.model.Device;

@Service
public class DeviceService {
	@Autowired
	private DeviceDao deviceDao;

	public List<Device> listDeviceByCompany(Long companyId, int page, int size) {
		return deviceDao.listDeviceByCompany(companyId, (page - 1) * size, size);
	}

	public int countDeviceByCompany(Long companyId) {
		return deviceDao.countDeviceByCompany(companyId);
	}
}
