package com.apr7.sponge.model;

import java.util.Date;

public class CompanyHistoryData {

	private String workshopName;
	private Date dateTime;
	private Float ph;
	private Float emissionLoad;

	public String getWorkshopName() {
		return workshopName;
	}

	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
}
