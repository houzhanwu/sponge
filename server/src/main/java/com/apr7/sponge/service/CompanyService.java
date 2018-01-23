package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.CompanyDao;
import com.apr7.sponge.dao.WorkshopDao;
import com.apr7.sponge.model.Company;
import com.apr7.sponge.model.Workshop;
import com.apr7.sponge.model.vo.CompanyWorkshopVO;

@Service
public class CompanyService {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private WorkshopDao workshopDao;

	public List<Company> listAllCompany() {
		return companyDao.listAllCompany();
	}

	public List<Workshop> listWorkshopByCompanyId(Long companyId) {
		return workshopDao.listWorkshopByCompanyId(companyId);
	}

	public List<CompanyWorkshopVO> listAllCompanyWorkshopVO() {
		return workshopDao.listAllCompanyWorkshopVO();
	}
}
