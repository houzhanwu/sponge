package com.apr7.sponge.model;

import java.util.Date;

public class RealTimeData {

	private String companyName;
	private String workshopName;
	private String areaName;
	private Long deviceId;
	private String rtdData;
	private String statusData;
	private Integer dataProtocol;
	private Date lmodify;

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

	public Date getLmodify() {
		return lmodify;
	}

	public void setLmodify(Date lmodify) {
		this.lmodify = lmodify;
	}
}
