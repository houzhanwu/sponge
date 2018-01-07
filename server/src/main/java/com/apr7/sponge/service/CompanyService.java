package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.CompanyDao;
import com.apr7.sponge.model.Company;

@Service
public class CompanyService {
	@Autowired
	private CompanyDao companyDao;

	public List<Company> listAllCompany() {
		return companyDao.listAllCompany();
	}
}
