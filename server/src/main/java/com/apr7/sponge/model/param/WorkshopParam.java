package com.apr7.sponge.model.param;

import com.apr7.sponge.model.Device;
import com.apr7.sponge.model.Workshop;

public class WorkshopParam {

	private Long id;
	private String name;
	private Long companyId;
	private String remarks;
	private String deviceMn;
	private Integer deviceStatus = 0;
	private String deviceIp;
	private Integer devicePort;
	private Integer deviceDataProtocol;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDeviceMn() {
		return deviceMn;
	}

	public void setDeviceMn(String deviceMn) {
		this.deviceMn = deviceMn;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public Integer getDevicePort() {
		return devicePort;
	}

	public void setDevicePort(Integer devicePort) {
		this.devicePort = devicePort;
	}

	public Integer getDeviceDataProtocol() {
		return deviceDataProtocol;
	}

	public void setDeviceDataProtocol(Integer deviceDataProtocol) {
		this.deviceDataProtocol = deviceDataProtocol;
	}

	public Workshop toWorkshopModel() {
		Workshop workshop = new Workshop();
		workshop.setId(this.getId());
		workshop.setName(this.getName());
		workshop.setCompanyId(this.getCompanyId());
		workshop.setRemarks(this.getRemarks());
		return workshop;
	}

	public Device toDeviceModel() {
		Device device = new Device();
		device.setId(this.getId());
		device.setMn(this.getDeviceMn());
		device.setStatus(this.getDeviceStatus());
		device.setWorkshopId(this.getId());
		device.setIp(this.getDeviceIp());
		device.setPort(this.getDevicePort());
		device.setDataProtocol(this.getDeviceDataProtocol());
		return device;
	}
}
