package com.apr7.sponge.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.constants.CompanyStatusEnum;
import com.apr7.sponge.model.RealTimeData;
import com.apr7.sponge.service.RealTimeDataService;

@Controller
@RequestMapping("/data")
public class RealTimeDataController {
	@Autowired
	private RealTimeDataService realTimeDataService;

	@RequestMapping("/realtime")
	@ResponseBody
	public List<RealTimeData> listDeviceByCompany() {
		List<RealTimeData> realTimeDatas = realTimeDataService.listAllRealTimeData();
		for (RealTimeData realTimeData : realTimeDatas) {
			if (realTimeData.getDeviceId() == null) {
				realTimeData.setStatus(CompanyStatusEnum.NOT_INSTALLED.getCode());
			} else if (realTimeData.getLmodify() == null) {
				realTimeData.setStatus(CompanyStatusEnum.OFFLINE.getCode());
			}
		}
		return realTimeDatas;
	}
}