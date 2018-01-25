package com.apr7.sponge.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apr7.sponge.dao.PollutantDao;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.utils.MapUtilsX;

@Service
public class PollutantService {
	@Autowired
	private PollutantDao pollutantDao;

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
		pollutantDao.deletePollutantMapping(pollutantId);
		pollutantDao.deletePollutant(pollutantId);
	}

	@Transactional
	public void reorderPollutant(Map<Long, Integer> reorderMap) {
		List<Long> pollutantIds = MapUtilsX.getKeys(reorderMap);
		pollutantDao.updateOrderToNegative(pollutantIds);
		for (Map.Entry<Long, Integer> entry : reorderMap.entrySet()) {
			pollutantDao.updateOrder(entry.getKey(), entry.getValue());
		}
	}

	public List<Pollutant> listAllPollutant() {
		return pollutantDao.listPollutant(null);
	}

	public List<Pollutant> listShowingPollutant() {
		return pollutantDao.listPollutant(true);
	}
}
