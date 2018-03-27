package com.apr7.sponge.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apr7.sponge.dao.DeviceDao;
import com.apr7.sponge.dao.WorkshopDao;
import com.apr7.sponge.dao.WorkshopPollutantMappingDao;
import com.apr7.sponge.model.Device;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.Workshop;
import com.apr7.sponge.model.WorkshopPollutantMapping;

@Service
public class WorkshopService {
	@Autowired
	private WorkshopDao workshopDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private WorkshopPollutantMappingDao workshopPollutantMappingDao;
	@Autowired
	private PollutantService pollutantService;

	@Transactional
	public void addWorkshop(Workshop workshop, Device device) {
		workshopDao.addWorkshop(workshop);
		device.setId(workshop.getId());
		device.setWorkshopId(workshop.getId());
		deviceDao.addDevice(device);
	}

	@Transactional
	public void deleteWorkshop(Long workshopId) {
		workshopPollutantMappingDao.deletePollutantByWorkshopId(workshopId, null);
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

	@Transactional
	public void saveWorkshopPollutantMappings(Long workshopId, List<Long> pollutantIds) {
		workshopPollutantMappingDao.updateStatusByWorkshopId(workshopId, 0, pollutantIds);
		if (CollectionUtils.isNotEmpty(pollutantIds)) {
			workshopPollutantMappingDao.saveWorkshopPollutantMappings(workshopId, pollutantIds);
		}
	}

	public List<WorkshopPollutantMapping> listWorkshopPollutantMappingByWorkshopIds(List<Long> workshopIds) {
		List<WorkshopPollutantMapping> workshopPollutantMappings = workshopPollutantMappingDao.listWorkshopPollutantMappingByWorkshopIds(workshopIds);
		List<Long> pollutantIds = new ArrayList<>(workshopPollutantMappings.size());
		for (WorkshopPollutantMapping workshopPollutantMapping : workshopPollutantMappings) {
			pollutantIds.add(workshopPollutantMapping.getPollutantId());
		}
		Map<Long, Pollutant> pollutantMap = pollutantService.getPollutants(pollutantIds);
		Map<Long, WorkshopPollutantMapping> filter = new HashMap<>();
		for (WorkshopPollutantMapping workshopPollutantMapping : workshopPollutantMappings) {
			Pollutant pollutant = pollutantMap.get(workshopPollutantMapping.getPollutantId());
			if (pollutant == null) {
				continue;
			}
			WorkshopPollutantMapping tmp = filter.get(workshopPollutantMapping.getPollutantId());
			if (tmp == null || workshopPollutantMapping.getStatus() == 0) {
				workshopPollutantMapping.setPollutant(pollutant);
				filter.put(workshopPollutantMapping.getPollutantId(), workshopPollutantMapping);
			}
		}
		List<WorkshopPollutantMapping> result = new ArrayList<>(filter.size());
		result.addAll(filter.values());
		Collections.sort(result, (o1, o2) -> {
			int p = o1.getPollutant().getOrder().compareTo(o2.getPollutant().getOrder());
			if (p == 0) {
				p = o1.getPollutantId().compareTo(o2.getPollutantId());
			}
			return p;
		});
		return result;
	}

	public void deletePollutantByWorkshopId(Long workshopId, Long pollutantId) {
		workshopPollutantMappingDao.deletePollutantByWorkshopId(workshopId, pollutantId);
	}
}
