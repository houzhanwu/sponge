package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.HistoryData;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.Workshop;
import com.apr7.sponge.model.vo.HistoryDataVO;
import com.apr7.sponge.service.CompanyService;
import com.apr7.sponge.service.HistoryDataService;
import com.apr7.sponge.service.PollutantService;
import com.apr7.sponge.service.protocol.ProtocolDataService;
import com.apr7.sponge.utils.DateUtilsX;

@Controller
@RequestMapping("/data")
public class HistoryDataController {
	@Autowired
	private HistoryDataService historyDataService;
	@Autowired
	private PollutantService pollutantService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProtocolDataService protocolDataService;

	@RequestMapping("/history")
	@ResponseBody
	public JSONObject history(Long companyId, Long workshopId, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime, int page) {
		final int dataFrequencyMins = 5;
		List<Workshop> workshops;
		if (workshopId != null) {
			workshops = Arrays.asList(companyService.getWorkshop(workshopId));
		} else {
			workshops = companyService.listWorkshopByCompanyId(companyId);
			Collections.sort(workshops, (o1, o2) -> {
				return o2.getId().compareTo(o1.getId());
			});
		}
		if (workshops.isEmpty()) {
			JSONObject value = new JSONObject();
			value.put("total", 0);
			value.put("data", new ArrayList<HistoryDataVO>());
			return value;
		}
		int multiple = (int) Math.ceil(30D / workshops.size());
		int size = multiple * workshops.size();
		Date pageStartTime = DateUtilsX.ceil(DateUtils.addMinutes(endTime, page * multiple * -dataFrequencyMins), TimeUnit.MINUTES,
				dataFrequencyMins);
		Date pageEndTime = DateUtilsX.ceil(DateUtils.addMinutes(endTime, (page - 1) * multiple * -dataFrequencyMins), TimeUnit.MINUTES, dataFrequencyMins);
		if (pageStartTime.before(startTime)) {
			pageStartTime = DateUtilsX.ceil(startTime, TimeUnit.MINUTES, dataFrequencyMins);
		}
		List<HistoryData> historyDatas = historyDataService.listHistoryDataByCompanyId(companyId, workshopId, pageStartTime, pageEndTime);
		List<Pollutant> pollutants = pollutantService.listShowingPollutant();
		List<HistoryDataVO> data = new ArrayList<>(size);
		Iterator<HistoryData> it = historyDatas.iterator();
		HistoryData historyData = null;
		if (it.hasNext()) {
			historyData = it.next();
		}
		for (Date currentTime = DateUtils.addMinutes(pageEndTime, -dataFrequencyMins); !currentTime.before(pageStartTime); currentTime = DateUtils
				.addMinutes(currentTime, -dataFrequencyMins)) {
			for (Workshop workshop : workshops) {
				JSONObject dataSet;
				if (historyData != null && currentTime.equals(DateUtilsX.floor(historyData.getDateTime(), TimeUnit.MINUTES, dataFrequencyMins))
						&& workshop.getId().equals(historyData.getWorkshopId())) {
					DataProtocolEnum dataProtocolEnum = DataProtocolEnum.fromCode(historyData.getDataProtocol());
					dataSet = protocolDataService.buildDataSet(dataProtocolEnum, pollutants, historyData.getRtdData(), historyData.getStatusData());
					if (it.hasNext()) {
						historyData = it.next();
					} else {
						historyData = null;
					}
				} else {
					dataSet = protocolDataService.buildDataSet(null, pollutants, null, null);
				}
				HistoryDataVO historyDataVO = new HistoryDataVO();
				historyDataVO.setWorkshopName(workshop.getName());
				historyDataVO.setDateTime(currentTime);
				historyDataVO.setDataSet(dataSet);
				data.add(historyDataVO);
			}
		}
		JSONObject value = new JSONObject();
		value.put("total", (DateUtilsX.ceil(endTime, TimeUnit.MINUTES, dataFrequencyMins).getTime()
				- DateUtilsX.ceil(startTime, TimeUnit.MINUTES, dataFrequencyMins).getTime()) / TimeUnit.MINUTES.toMillis(dataFrequencyMins) * workshops.size());
		value.put("data", data);
		return value;
	}
}