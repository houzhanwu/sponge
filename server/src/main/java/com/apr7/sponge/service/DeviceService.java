package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apr7.sponge.dao.DeviceDao;
import com.apr7.sponge.model.Device;

public class DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	public List<Device> listActiveDevice() {
		return deviceDao.listActiveDevice();
	}
}
