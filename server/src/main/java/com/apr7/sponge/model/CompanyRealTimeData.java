package com.apr7.sponge.model;

import java.util.Date;

public class CompanyRealTimeData {

	private Long companyId;
	private String companyName;
	private String areaName;
	private Integer status;
	private Float ph;
	private Float emissionLoad;
	private Date lmodify;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Float getPh() {
		return ph;
	}

	public void setPh(Float ph) {
		this.ph = ph;
	}

	public Float getEmissionLoad() {
		return emissionLoad;
	}

	public void setEmissionLoad(Float emissionLoad) {
		this.emissionLoad = emissionLoad;
	}

	public Date getLmodify() {
		return lmodify;
	}

	public void setLmodify(Date lmodify) {
		this.lmodify = lmodify;
	}
}
