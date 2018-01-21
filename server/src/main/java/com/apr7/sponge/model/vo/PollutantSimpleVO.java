package com.apr7.sponge.model.vo;

import com.apr7.sponge.model.Pollutant;

public class PollutantSimpleVO {

	private Long id;
	private String name;

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

	public static PollutantSimpleVO build(Pollutant pollutant) {
		PollutantSimpleVO pollutantSimpleVO = new PollutantSimpleVO();
		pollutantSimpleVO.setId(pollutant.getId());
		pollutantSimpleVO.setName(pollutant.getName());
		return pollutantSimpleVO;
	}
}
