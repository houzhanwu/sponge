package com.apr7.sponge.model;

import java.util.Date;

public class HistoryData {

	private Long workshopId;
	private Date dateTime;
	private Long companyId;
	private String rtdData;
	private String statusData;
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

	public String getRtdData() {
		return rtdData;
	}

	public void setRtdData(String rtdData) {
		this.rtdData = rtdData;
	}

	public String getStatusData() {
		return statusData;
	}

	public void setStatusData(String statusData) {
		this.statusData = statusData;
	}

	public Integer getDataProtocol() {
		return dataProtocol;
	}

	public void setDataProtocol(Integer dataProtocol) {
		this.dataProtocol = dataProtocol;
	}
}
