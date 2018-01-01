package com.apr7.sponge.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.apr7.sponge.model.Device;
import com.apr7.sponge.service.DeviceService;

@Controller
@RequestMapping("/user")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;

	@RequestMapping("/listDeviceByCompany")
	@ResponseBody
	public String listDeviceByCompany(Long companyId, int page, int size) {
		List<Device> devices = deviceService.listDeviceByCompany(companyId, page, size);
		return JSON.toJSONString(devices);
	}
}