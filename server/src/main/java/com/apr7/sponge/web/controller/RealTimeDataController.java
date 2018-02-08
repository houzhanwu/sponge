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
import com.apr7.sponge.service.protocol.ProtocolDataService;

@Controller
@RequestMapping("/data")
public class RealTimeDataController {
	@Autowired
	private RealTimeDataService realTimeDataService;
	@Autowired
	private PollutantService pollutantService;
	@Autowired
	private ProtocolDataService protocolDataService;

	@RequestMapping("/realtime")
	@ResponseBody
	public List<RealTimeDataVO> listDeviceByCompany() {
		List<RealTimeData> realTimeDatas = realTimeDataService.listAllRealTimeData();
		List<Pollutant> pollutants = pollutantService.listShowingPollutant();
		List<RealTimeDataVO> data = new ArrayList<>(realTimeDatas.size());
		for (RealTimeData realTimeData : realTimeDatas) {
			DataProtocolEnum dataProtocolEnum = DataProtocolEnum.fromCode(realTimeData.getDataProtocol());
			JSONObject dataSet = protocolDataService.buildDataSet(dataProtocolEnum, pollutants, realTimeData.getRtdData(), realTimeData.getStatusData());
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