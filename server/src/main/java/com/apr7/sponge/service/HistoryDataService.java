package com.apr7.sponge.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.CompanyHistoryDataDao;
import com.apr7.sponge.model.CompanyHistoryData;

@Service
public class HistoryDataService {
	@Autowired
	private CompanyHistoryDataDao companyHistoryDataDao;

	public List<CompanyHistoryData> listCompanyHistoryData(Long companyId, Date startTime, Date endTime) {
		return companyHistoryDataDao.listCompanyHistoryData(companyId, startTime, endTime);
	}
}
