package com.apr7.sponge.model.vo;

import com.apr7.sponge.model.Pollutant;

public class PollutantVO {

	private Long id;
	private String name;
	private Boolean show;
	private Integer order;
	private String fieldKeyHjt212;
	private String fieldKeyKnt2014;

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

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getFieldKeyHjt212() {
		return fieldKeyHjt212;
	}

	public void setFieldKeyHjt212(String fieldKeyHjt212) {
		this.fieldKeyHjt212 = fieldKeyHjt212;
	}

	public String getFieldKeyKnt2014() {
		return fieldKeyKnt2014;
	}

	public void setFieldKeyKnt2014(String fieldKeyKnt2014) {
		this.fieldKeyKnt2014 = fieldKeyKnt2014;
	}

	public static PollutantVO build(Pollutant pollutant) {
		PollutantVO pollutantVO = new PollutantVO();
		pollutantVO.setId(pollutant.getId());
		pollutantVO.setName(pollutant.getName());
		pollutantVO.setShow(pollutant.getShow());
		pollutantVO.setOrder(pollutant.getOrder());
		pollutantVO.setFieldKeyHjt212(pollutant.getMapping().getFieldKeyHjt212());
		pollutantVO.setFieldKeyKnt2014(pollutant.getMapping().getFieldKeyKnt2014());
		return pollutantVO;
	}
}
