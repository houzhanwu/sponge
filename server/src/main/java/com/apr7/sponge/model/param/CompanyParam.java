package com.apr7.sponge.model.param;

import com.apr7.sponge.model.Company;

public class CompanyParam {

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

	public Company toModel() {
		Company company = new Company();
		company.setId(this.getId());
		company.setName(this.getName());
		company.setAreaId(this.getAreaId());
		return company;
	}
}
