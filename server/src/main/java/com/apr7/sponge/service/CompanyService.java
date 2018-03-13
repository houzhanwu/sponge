package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.CompanyDao;
import com.apr7.sponge.dao.CompanyPollutantMappingDao;
import com.apr7.sponge.model.Company;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.utils.MultipageList;
import com.apr7.sponge.utils.SqlUtils;

@Service
public class CompanyService {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private CompanyPollutantMappingDao companyPollutantMappingDao;

	public void addCompany(Company company) {
		companyDao.addCompany(company);
	}

	public void deleteCompany(Long companyId) {
		companyDao.deleteCompany(companyId);
	}

	public void updateCompany(Company company) {
		companyDao.updateCompany(company);
	}

	public Company getCompany(Long companyId) {
		return companyDao.getCompany(companyId);
	}

	public MultipageList<Company> listCompany(String keyword, int page, int size) {
		keyword = SqlUtils.escapeSQLLike(keyword);
		MultipageList<Company> multipageList = new MultipageList<>();
		multipageList.setData(companyDao.listCompany(keyword, (page - 1) * size, size));
		multipageList.setTotal(companyDao.countCompany(keyword));
		return multipageList;
	}

	public List<Company> listAllCompany() {
		return companyDao.listAllCompany();
	}

	public List<Pollutant> listPollutantByCompanyId(Long companyId) {
		return companyPollutantMappingDao.listPollutantByCompanyId(companyId);
	}
}
