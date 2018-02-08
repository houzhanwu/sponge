package com.apr7.sponge.service.protocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;

public class ProtocolDataService {

	private Map<DataProtocolEnum, ProtocolDataAdapterInter> protocolDataAdapterMap = new HashMap<>();
	private ProtocolDataAdapterInter defaultProtocolDataAdapter;

	public void setProtocolDataAdapters(List<ProtocolDataAdapterInter> protocolDataAdapters) {
		for (ProtocolDataAdapterInter protocolDataAdapter : protocolDataAdapters) {
			this.protocolDataAdapterMap.put(protocolDataAdapter.getDataProtocol(), protocolDataAdapter);
		}
	}

	public void setDefaultProtocolDataAdapter(ProtocolDataAdapterInter defaultProtocolDataAdapter) {
		this.defaultProtocolDataAdapter = defaultProtocolDataAdapter;
	}

	public JSONObject buildDataSet(DataProtocolEnum dataProtocolEnum, List<Pollutant> pollutants, String rtdData, String statusData) {
		ProtocolDataAdapterInter protocolDataAdapter = protocolDataAdapterMap.get(dataProtocolEnum);
		if (protocolDataAdapter == null) {
			protocolDataAdapter = defaultProtocolDataAdapter;
		}
		return protocolDataAdapter.buildDataSet(pollutants, rtdData, statusData);
	}
}
