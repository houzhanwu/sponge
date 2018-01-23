package com.apr7.sponge.model.vo;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class HistoryDataVO {

	private String workshopName;
	private Date dateTime;
	private JSONObject dataSet;

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

	public JSONObject getDataSet() {
		return dataSet;
	}

	public void setDataSet(JSONObject dataSet) {
		this.dataSet = dataSet;
	}
}
