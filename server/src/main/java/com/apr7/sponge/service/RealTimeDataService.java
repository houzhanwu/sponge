package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.CompanyRealTimeDataDao;
import com.apr7.sponge.model.CompanyRealTimeData;

@Service
public class RealTimeDataService {
	@Autowired
	private CompanyRealTimeDataDao companyRealTimeDataDao;

	public List<CompanyRealTimeData> listAllCompanyRealTimeData() {
		return companyRealTimeDataDao.listAllCompanyRealTimeData();
	}
}
