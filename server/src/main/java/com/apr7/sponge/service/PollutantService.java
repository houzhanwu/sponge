package com.apr7.sponge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.dao.PollutantDao;
import com.apr7.sponge.dao.WorkshopPollutantMappingDao;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.utils.MapUtilsX;

@Service
public class PollutantService {
	@Autowired
	private PollutantDao pollutantDao;
	@Autowired
	private WorkshopPollutantMappingDao workshopPollutantMappingDao;

	@Transactional
	public void addPollutant(Pollutant pollutant) {
		int maxOrder = pollutantDao.getMaxOrder();
		pollutant.setOrder(maxOrder);
		pollutantDao.addPollutant(pollutant);
		pollutant.getMapping().setPollutantId(pollutant.getId());
		pollutantDao.addPollutantMapping(pollutant.getMapping());
	}

	@Transactional
	public void deletePollutant(Long pollutantId) {
		workshopPollutantMappingDao.deletePollutant(pollutantId);
		pollutantDao.deletePollutantMapping(pollutantId);
		pollutantDao.deletePollutant(pollutantId);
	}

	@Transactional
	public void updatePollutant(Pollutant pollutant) {
		pollutantDao.updatePollutant(pollutant);
		pollutant.getMapping().setPollutantId(pollutant.getId());
		pollutantDao.updatePollutantMapping(pollutant.getMapping());
	}

	@Transactional
	public void reorderPollutant(Map<Long, Integer> reorderMap) {
		List<Long> pollutantIds = MapUtilsX.getKeys(reorderMap);
		pollutantDao.updateOrderToNegative(pollutantIds);
		for (Map.Entry<Long, Integer> entry : reorderMap.entrySet()) {
			pollutantDao.updateOrder(entry.getKey(), entry.getValue());
		}
	}

	public Map<Long, Pollutant> getPollutants(List<Long> pollutantIds) {
		Map<Long, Pollutant> pollutantMap = new HashMap<>();
		if (CollectionUtils.isEmpty(pollutantIds)) {
			return pollutantMap;
		}
		List<Pollutant> pollutants = pollutantDao.listPollutantByIds(pollutantIds);
		for (Pollutant pollutant : pollutants) {
			pollutantMap.put(pollutant.getId(), pollutant);
		}
		return pollutantMap;
	}

	public List<Pollutant> listAllPollutant() {
		return pollutantDao.listPollutant(null);
	}

	public List<Pollutant> listShowingPollutant() {
		return pollutantDao.listPollutant(true);
	}

	public List<Long> listPollutantIdsByFieldKeys(DataProtocolEnum dataProtocol, List<String> fieldKeys) {
		if (CollectionUtils.isEmpty(fieldKeys)) {
			return new ArrayList<>();
		}
		String fieldName;
		switch (dataProtocol) {
		case HJT212:
			fieldName = "FFIELD_KEY_HJT212";
			break;
		case KNT2014:
			fieldName = "FFIELD_KEY_KNT2014";
			break;
		default:
			throw new IllegalArgumentException();
		}
		return pollutantDao.listPollutantIdsByFieldKeys(fieldName, fieldKeys);
	}
}
