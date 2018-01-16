package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.HistoryData;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.vo.HistoryDataVO;
import com.apr7.sponge.service.HistoryDataService;
import com.apr7.sponge.service.PollutantService;

@Controller
@RequestMapping("/data")
public class HistoryDataController {
	@Autowired
	private HistoryDataService historyDataService;
	@Autowired
	private PollutantService pollutantService;

	@RequestMapping("/history")
	@ResponseBody
	public List<HistoryDataVO> history(Long companyId, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
		List<HistoryData> historyDatas = historyDataService.listHistoryDataByCompanyId(companyId, startTime, endTime);
		Collections.sort(historyDatas, (o1, o2) -> {
			int result = o2.getDateTime().compareTo(o1.getDateTime());
			if (result == 0) {
				result = o1.getWorkshopName().compareTo(o2.getWorkshopName());
			}
			return result;
		});
		historyDatas = new LinkedList<>(historyDatas);
		List<HistoryDataVO> result = new ArrayList<>(historyDatas.size());
		List<Pollutant> pollutants = pollutantService.listShowingPollutant();
		for (Iterator<HistoryData> it = historyDatas.iterator(); it.hasNext();) {
			HistoryData historyData = it.next();
			DataProtocolEnum dataProtocolEnum = DataProtocolEnum.fromCode(historyData.getDataProtocol());
			if (dataProtocolEnum == null) {
				continue;
			}
			JSONObject rtdData = historyData.getRtdData();
			JSONObject statusData = historyData.getStatusData();
			JSONObject dataSet = new JSONObject();
			for (Pollutant pollutant : pollutants) {
				String value = null;
				switch (dataProtocolEnum) {
				case HJT212:
					value = rtdData.getString(pollutant.getMapping().getFieldKeyHjt212());
					if (value == null) {
						value = statusData.getString(pollutant.getMapping().getFieldKeyHjt212());
					}
					break;
				case KNT2014:
					value = rtdData.getString(pollutant.getMapping().getFieldKeyKnt2014());
					if (value == null) {
						value = statusData.getString(pollutant.getMapping().getFieldKeyKnt2014());
					}
					break;
				default:
					break;
				}
				dataSet.put("_" + pollutant.getId(), value);
			}
			result.add(HistoryDataVO.build(historyData, dataSet));
			it.remove();
		}
		return result;
	}
}