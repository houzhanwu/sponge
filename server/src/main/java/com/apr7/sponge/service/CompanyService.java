package com.apr7.sponge.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apr7.sponge.dao.CompanyDao;
import com.apr7.sponge.dao.WorkshopDao;
import com.apr7.sponge.model.Company;
import com.apr7.sponge.model.WorkshopPollutantMapping;
import com.apr7.sponge.utils.MultipageList;
import com.apr7.sponge.utils.SqlUtils;

@Service
public class CompanyService {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private WorkshopDao workshopDao;
	@Autowired
	private WorkshopService workshopService;

	public void addCompany(Company company) {
		companyDao.addCompany(company);
	}

	@Transactional
	public void deleteCompany(Long companyId) {
		List<Long> workshopIds = workshopDao.listWorkshopIdsByCompanyId(companyId);
		for (Long workshopId : workshopIds) {
			workshopService.deleteWorkshop(workshopId);
		}
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

	public List<WorkshopPollutantMapping> listWorkshopPollutantMappingByCompanyId(Long companyId) {
		List<Long> workshopIds = workshopDao.listWorkshopIdsByCompanyId(companyId);
		if (CollectionUtils.isEmpty(workshopIds)) {
			return new ArrayList<>();
		}
		return workshopService.listWorkshopPollutantMappingByWorkshopIds(workshopIds);
	}
}
