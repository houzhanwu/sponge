package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.PollutantDao;
import com.apr7.sponge.model.Pollutant;

@Service
public class PollutantService {
	@Autowired
	private PollutantDao pollutantDao;

	public List<Pollutant> listAllPollutant() {
		return pollutantDao.listPollutant(null);
	}

	public List<Pollutant> listShowingPollutant() {
		return pollutantDao.listPollutant(true);
	}
}
