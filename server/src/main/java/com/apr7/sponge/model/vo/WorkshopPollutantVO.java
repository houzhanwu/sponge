package com.apr7.sponge.model.vo;

import com.apr7.sponge.model.WorkshopPollutantMapping;

public class WorkshopPollutantVO {

	private Long id;
	private String name;
	private String type;
	private Integer status;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static WorkshopPollutantVO build(WorkshopPollutantMapping workshopPollutantMapping) {
		WorkshopPollutantVO workshopPollutantVO = new WorkshopPollutantVO();
		workshopPollutantVO.setId(workshopPollutantMapping.getPollutantId());
		workshopPollutantVO.setName(workshopPollutantMapping.getPollutant().getName());
		workshopPollutantVO.setType(workshopPollutantMapping.getPollutant().getType().name());
		workshopPollutantVO.setStatus(workshopPollutantMapping.getStatus());
		return workshopPollutantVO;
	}
}
