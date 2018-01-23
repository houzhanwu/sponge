package com.apr7.sponge.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.HistoryDataDao;
import com.apr7.sponge.model.HistoryData;

@Service
public class HistoryDataService {
	@Autowired
	private HistoryDataDao historyDataDao;

	public void addHistoryDatas(List<HistoryData> historyDatas) {
		historyDataDao.addHistoryDatas(historyDatas);
	}

	public List<HistoryData> listHistoryDataByCompanyId(Long companyId, Long workshopId, Date startTime, Date endTime) {
		return historyDataDao.listHistoryDataByCompanyId(companyId, workshopId, startTime, endTime);
	}
}
