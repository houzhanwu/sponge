package com.apr7.sponge.model;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class HistoryData {

	private Long workshopId;
	private Date dateTime;
	private Long companyId;
	private JSONObject rtdData;
	private JSONObject statusData;
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

	public JSONObject getRtdData() {
		return rtdData == null ? new JSONObject() : rtdData;
	}

	public void setRtdData(JSONObject rtdData) {
		this.rtdData = rtdData;
	}

	public JSONObject getStatusData() {
		return statusData == null ? new JSONObject() : statusData;
	}

	public void setStatusData(JSONObject statusData) {
		this.statusData = statusData;
	}

	public Integer getDataProtocol() {
		return dataProtocol;
	}

	public void setDataProtocol(Integer dataProtocol) {
		this.dataProtocol = dataProtocol;
	}
}
