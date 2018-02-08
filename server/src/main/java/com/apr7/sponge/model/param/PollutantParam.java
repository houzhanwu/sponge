package com.apr7.sponge.model.param;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.apr7.sponge.constants.PollutantTypeEnum;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.PollutantMapping;

public class PollutantParam {

	private Long id;
	private String name;
	private PollutantTypeEnum type;
	private Boolean show;
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

	public PollutantTypeEnum getType() {
		return type;
	}

	public void setType(PollutantTypeEnum type) {
		this.type = type;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
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

	public Pollutant toModel() {
		Pollutant pollutant = new Pollutant();
		pollutant.setId(this.getId());
		pollutant.setName(this.getName());
		pollutant.setType(this.getType());
		pollutant.setShow(ObjectUtils.defaultIfNull(this.getShow(), Boolean.FALSE));
		pollutant.setOrder(0);
		PollutantMapping mapping = new PollutantMapping();
		mapping.setPollutantId(this.getId());
		mapping.setFieldKeyHjt212(StringUtils.isBlank(this.getFieldKeyHjt212()) ? null : this.getFieldKeyHjt212());
		mapping.setFieldKeyKnt2014(StringUtils.isBlank(this.getFieldKeyKnt2014()) ? null : this.getFieldKeyKnt2014());
		pollutant.setMapping(mapping);
		return pollutant;
	}
}
