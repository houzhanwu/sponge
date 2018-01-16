package com.apr7.sponge.model;

public class Pollutant {

	private Long id;
	private String name;
	private Boolean show;
	private Integer order;
	private PollutantMapping mapping;

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

	public PollutantMapping getMapping() {
		return mapping;
	}

	public void setMapping(PollutantMapping mapping) {
		this.mapping = mapping;
	}
}
