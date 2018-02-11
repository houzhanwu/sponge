package com.apr7.sponge.model.vo;

import com.apr7.sponge.model.Company;

public class CompanyVO {

	private Long id;
	private String name;
	private Long areaId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public static CompanyVO build(Company company) {
		CompanyVO companyVO = new CompanyVO();
		companyVO.setId(company.getId());
		companyVO.setName(company.getName());
		companyVO.setAreaId(company.getAreaId());
		return companyVO;
	}
}
