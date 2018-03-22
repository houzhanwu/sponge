package com.apr7.sponge.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apr7.sponge.dao.DeviceDao;
import com.apr7.sponge.dao.WorkshopDao;
import com.apr7.sponge.dao.WorkshopPollutantMappingDao;
import com.apr7.sponge.model.Device;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.Workshop;

@Service
public class WorkshopService {
	@Autowired
	private WorkshopDao workshopDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private WorkshopPollutantMappingDao workshopPollutantMappingDao;

	@Transactional
	public void addWorkshop(Workshop workshop, Device device) {
		workshopDao.addWorkshop(workshop);
		deviceDao.addDevice(device);
	}

	@Transactional
	public void deleteWorkshop(Long workshopId) {
		deviceDao.deleteDeviceByWorkshopId(workshopId);
		workshopDao.deleteWorkshop(workshopId);
	}

	@Transactional
	public void updateWorkshop(Workshop workshop, Device device) {
		workshopDao.updateWorkshop(workshop);
		deviceDao.updateDevice(device);
	}

	public Workshop getWorkshop(Long workshopId) {
		return workshopDao.getWorkshop(workshopId);
	}

	public Device getDeviceByWorkshopId(Long workshopId) {
		return deviceDao.getDeviceByWorkshopId(workshopId);
	}

	public Map<Long, Device> getDevicesByWorkshopIds(List<Long> workshopIds) {
		Map<Long, Device> workshopIdsDevicesMap = new HashMap<>();
		if (workshopIds.isEmpty()) {
			return workshopIdsDevicesMap;
		}
		List<Device> devices = deviceDao.listDeviceByWorkshopIds(workshopIds);
		for (Device device : devices) {
			workshopIdsDevicesMap.put(device.getWorkshopId(), device);
		}
		return workshopIdsDevicesMap;
	}

	public String getDeviceMnByWorkshopId(Long workshopId) {
		return deviceDao.getMnByWorkshopId(workshopId);
	}

	public Long getWorkshopIdByDeviceMn(String deviceMn) {
		return deviceDao.getWorkshopIdByMn(deviceMn);
	}

	public Workshop getWorkshopByDeviceMn(String deviceMn) {
		return deviceDao.getWorkshopByMn(deviceMn);
	}

	public List<Workshop> listWorkshopNameByCompanyId(Long companyId) {
		return workshopDao.listWorkshopByCompanyId(companyId);
	}

	public List<Workshop> listWorkshopByCompanyId(Long companyId) {
		return workshopDao.listWorkshopByCompanyId(companyId);
	}

	public void addWorkshopPollutantMappings(Long workshopId, List<Long> pollutantIds) {
		workshopPollutantMappingDao.addWorkshopPollutantMappings(workshopId, pollutantIds);
	}

	public List<Pollutant> listPollutantByWorkshopId(Long workshopId) {
		return workshopPollutantMappingDao.listPollutantByWorkshopIds(Arrays.asList(workshopId));
	}

	public void deletePollutantByWorkshopId(Long workshopId, Long pollutantId) {
		workshopPollutantMappingDao.deletePollutantByWorkshopId(workshopId, pollutantId);
	}
}
