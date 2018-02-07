package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.RealTimeData;
import com.apr7.sponge.model.vo.RealTimeDataVO;
import com.apr7.sponge.service.PollutantService;
import com.apr7.sponge.service.RealTimeDataService;

@Controller
@RequestMapping("/data")
public class RealTimeDataController {
	@Autowired
	private RealTimeDataService realTimeDataService;
	@Autowired
	private PollutantService pollutantService;

	@RequestMapping("/realtime")
	@ResponseBody
	public List<RealTimeDataVO> listDeviceByCompany() {
		List<RealTimeData> realTimeDatas = realTimeDataService.listAllRealTimeData();
		List<Pollutant> pollutants = pollutantService.listShowingPollutant();
		List<RealTimeDataVO> data = new ArrayList<>(realTimeDatas.size());
		for (RealTimeData realTimeData : realTimeDatas) {
			JSONObject dataSet = new JSONObject();
			DataProtocolEnum dataProtocolEnum;
			if (realTimeData.getDataProtocol() == null || (dataProtocolEnum = DataProtocolEnum.fromCode(realTimeData.getDataProtocol())) == null) {
				for (Pollutant pollutant : pollutants) {
					String value = null;
					dataSet.put("_" + pollutant.getId(), value);
				}
			} else {
				JSONObject rtdData = realTimeData.getRtdData();
				JSONObject statusData = realTimeData.getStatusData();
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
			}
			RealTimeDataVO realTimeDataVO = new RealTimeDataVO();
			realTimeDataVO.setCompanyName(realTimeData.getCompanyName());
			realTimeDataVO.setWorkshopName(realTimeData.getWorkshopName());
			realTimeDataVO.setAreaName(realTimeData.getAreaName());
			realTimeDataVO.setLmodify(realTimeData.getLmodify());
			realTimeDataVO.setDataSet(dataSet);
			data.add(realTimeDataVO);
		}
		return data;
	}
}