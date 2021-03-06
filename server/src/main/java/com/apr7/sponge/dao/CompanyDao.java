package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.Company;

@Mapper
public interface CompanyDao {
	void addCompany(Company company);

	void deleteCompany(@Param("companyId") Long companyId);

	void updateCompany(Company company);

	Company getCompany(@Param("companyId") Long companyId);

	List<Company> listCompany(@Param("keyword") String keyword, @Param("start") int start, @Param("max") int max);

	int countCompany(@Param("keyword") String keyword);

	List<Company> listAllCompany();
}
