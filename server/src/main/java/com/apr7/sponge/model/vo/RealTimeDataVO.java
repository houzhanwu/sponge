package com.apr7.sponge.model.vo;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class RealTimeDataVO {

	private String companyName;
	private String workshopName;
	private String areaName;
	private Long deviceId;
	private Integer status;
	private Float ph;
	private Float emissionLoad;
	private JSONObject rtdData;
	private JSONObject statusData;
	private Integer dataProtocol;
	private Date lmodify;
	private JSONObject dataSet;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWorkshopName() {
		return workshopName;
	}

	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public JSONObject getRtdData() {
		return rtdData;
	}

	public void setRtdData(JSONObject rtdData) {
		this.rtdData = rtdData;
	}

	public JSONObject getStatusData() {
		return statusData;
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

	public Date getLmodify() {
		return lmodify;
	}

	public void setLmodify(Date lmodify) {
		this.lmodify = lmodify;
	}

	public JSONObject getDataSet() {
		return dataSet;
	}

	public void setDataSet(JSONObject dataSet) {
		this.dataSet = dataSet;
	}
}
