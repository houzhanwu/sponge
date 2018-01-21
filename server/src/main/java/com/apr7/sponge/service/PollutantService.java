package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apr7.sponge.dao.PollutantDao;
import com.apr7.sponge.model.Pollutant;

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

	public List<Pollutant> listAllPollutant() {
		return pollutantDao.listPollutant(null);
	}

	public List<Pollutant> listShowingPollutant() {
		return pollutantDao.listPollutant(true);
	}
}
