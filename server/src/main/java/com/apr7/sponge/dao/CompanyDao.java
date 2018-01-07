package com.apr7.sponge.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Company;

@MapperScan
public interface CompanyDao {
	List<Company> listCompany(int start, int max);

	List<Company> listAllCompany();
}
