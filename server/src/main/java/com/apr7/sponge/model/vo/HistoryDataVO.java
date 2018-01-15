package com.apr7.sponge.model.vo;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.model.HistoryData;

public class HistoryDataVO {

	private String workshopName;
	private Date dateTime;
	private Float ph;
	private Float emissionLoad;
	private JSONObject dataSet;
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

	public JSONObject getDataSet() {
		return dataSet;
	}

	public void setDataSet(JSONObject dataSet) {
		this.dataSet = dataSet;
	}

	public Integer getDataProtocol() {
		return dataProtocol;
	}

	public void setDataProtocol(Integer dataProtocol) {
		this.dataProtocol = dataProtocol;
	}

	public static HistoryDataVO build(HistoryData historyData, JSONObject dataSet) {
		HistoryDataVO historyDataVO = new HistoryDataVO();
		historyDataVO.setWorkshopName(historyData.getWorkshopName());
		historyDataVO.setDateTime(historyData.getDateTime());
		historyDataVO.setDataSet(dataSet);
		historyDataVO.setDataProtocol(historyData.getDataProtocol());
		return historyDataVO;
	}
}
