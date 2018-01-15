package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.RealTimeDataDao;
import com.apr7.sponge.model.RealTimeData;

@Service
public class RealTimeDataService {
	@Autowired
	private RealTimeDataDao realTimeDataDao;

	public List<RealTimeData> listAllRealTimeData() {
		return realTimeDataDao.listAllRealTimeData();
	}
}
