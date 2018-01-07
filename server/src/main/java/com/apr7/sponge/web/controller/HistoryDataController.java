package com.apr7.sponge.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.CompanyHistoryData;
import com.apr7.sponge.service.HistoryDataService;

@Controller
@RequestMapping("/data")
public class HistoryDataController {
	@Autowired
	private HistoryDataService historyDataService;

	@RequestMapping("/history")
	@ResponseBody
	public List<CompanyHistoryData> history(Long companyId, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
		List<CompanyHistoryData> companyHistoryDatas = historyDataService.listCompanyHistoryData(companyId, startTime, endTime);
		return companyHistoryDatas;
	}
}