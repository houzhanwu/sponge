package com.apr7.sponge.model;

public class WorkshopPollutantMapping {

	private Long workshopId;
	private Long pollutantId;
	private Integer status;
	private Pollutant pollutant;

	public Long getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(Long workshopId) {
		this.workshopId = workshopId;
	}

	public Long getPollutantId() {
		return pollutantId;
	}

	public void setPollutantId(Long pollutantId) {
		this.pollutantId = pollutantId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Pollutant getPollutant() {
		return pollutant;
	}

	public void setPollutant(Pollutant pollutant) {
		this.pollutant = pollutant;
	}
}
