package com.apr7.sponge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.HistoryDataDao;
import com.apr7.sponge.model.HistoryData;
import com.apr7.sponge.utils.DateUtilsX;

@Service
public class HistoryDataService {
	@Autowired
	private HistoryDataDao historyDataDao;

	public void addHistoryData(HistoryData historyData) {
		historyDataDao.addHistoryData(DateUtilsX.getYear(historyData.getDateTime()), historyData);
	}

	public List<HistoryData> listHistoryDataByCompanyId(Long companyId, Long workshopId, Date startTime, Date endTime) {
		int startYear = DateUtilsX.getYear(startTime);
		int endYear = DateUtilsX.getYear(endTime);
		if (startYear == endYear) {
			return this.listHistoryDataByCompanyId(startYear, companyId, workshopId, startTime, endTime);
		}
		List<HistoryData> historyDatas = new ArrayList<>();
		for (int y = startYear; y <= endYear; y++) {
			historyDatas.addAll(this.listHistoryDataByCompanyId(y, companyId, workshopId, startTime, endTime));
		}
		return historyDatas;
	}

	private List<HistoryData> listHistoryDataByCompanyId(int year, Long companyId, Long workshopId, Date startTime, Date endTime) {
		if (!historyDataDao.existsTable(year)) {
			return new ArrayList<>();
		}
		return historyDataDao.listHistoryDataByCompanyId(year, companyId, workshopId, startTime, endTime);
	}

	public void createHistoryTable(int year) {
		if (!historyDataDao.existsTable(year)) {
			historyDataDao.createHistoryTable(year);
		}
	}
}
