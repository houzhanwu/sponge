package com.apr7.sponge.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.CompanyRealTimeData;
import com.apr7.sponge.service.RealTimeDataService;

@Controller
@RequestMapping("/data")
public class RealTimeDataController {
	@Autowired
	private RealTimeDataService realTimeDataService;

	@RequestMapping("/realtime")
	@ResponseBody
	public List<CompanyRealTimeData> listDeviceByCompany(HttpServletResponse response) {
		List<CompanyRealTimeData> companyRealTimeDatas = realTimeDataService.listAllCompanyRealTimeData();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return companyRealTimeDatas;
	}
}