package com.apr7.sponge.model.vo;

import com.apr7.sponge.model.Pollutant;

public class PollutantVO {

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

	public static PollutantVO build(Pollutant pollutant) {
		PollutantVO pollutantVO = new PollutantVO();
		pollutantVO.setId(pollutant.getId());
		pollutantVO.setName(pollutant.getName());
		return pollutantVO;
	}
}
