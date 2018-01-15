package com.apr7.sponge.model;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class HistoryData {

	private String workshopName;
	private Date dateTime;
	private JSONObject rtdData;
	private JSONObject statusData;
	private Integer dataProtocol;

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
