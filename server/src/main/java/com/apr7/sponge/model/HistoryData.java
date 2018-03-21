package com.apr7.sponge.model;

import java.util.Date;

public class HistoryData {

	private Long workshopId;
	private Date dateTime;
	private Long companyId;
	private String data;
	private Integer dataProtocol;

	public Long getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(Long workshopId) {
		this.workshopId = workshopId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getDataProtocol() {
		return dataProtocol;
	}

	public void setDataProtocol(Integer dataProtocol) {
		this.dataProtocol = dataProtocol;
	}
}
