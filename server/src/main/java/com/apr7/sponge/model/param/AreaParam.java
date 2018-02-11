package com.apr7.sponge.model.param;

import com.apr7.sponge.model.Area;

public class AreaParam {

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

	public Area toModel() {
		Area area = new Area();
		area.setId(this.getId());
		area.setName(this.getName());
		return area;
	}
}
