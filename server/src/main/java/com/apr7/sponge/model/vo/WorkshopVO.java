package com.apr7.sponge.model.vo;

import com.apr7.sponge.model.Workshop;

public class WorkshopVO {

	private Long id;
	private String name;
	private Long companyId;
	private String remarks;
	private String deviceMn;

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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDeviceMn() {
		return deviceMn;
	}

	public void setDeviceMn(String deviceMn) {
		this.deviceMn = deviceMn;
	}

	public static WorkshopVO build(Workshop workshop) {
		WorkshopVO workshopVO = new WorkshopVO();
		workshopVO.setId(workshop.getId());
		workshopVO.setName(workshop.getName());
		workshopVO.setCompanyId(workshop.getCompanyId());
		workshopVO.setRemarks(workshop.getRemarks());
		workshopVO.setDeviceMn(workshop.getMn());
		return workshopVO;
	}
}
