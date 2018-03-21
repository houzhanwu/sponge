package com.apr7.sponge.service.protocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;

public class ProtocolDataService {

	private Map<DataProtocolEnum, ProtocolRealtimeDataAdapterInter> protocolRealtimeDataAdapterMap = new HashMap<>();
	private ProtocolRealtimeDataAdapterInter defaultProtocolRealtimeDataAdapter;

	private Map<DataProtocolEnum, ProtocolHistoryDataAdapterInter> protocolHistoryDataAdapterMap = new HashMap<>();
	private ProtocolHistoryDataAdapterInter defaultProtocolHistoryDataAdapter;

	public void setProtocolRealtimeDataAdapters(List<ProtocolRealtimeDataAdapterInter> protocolRealtimeDataAdapters) {
		for (ProtocolRealtimeDataAdapterInter protocolRealtimeDataAdapter : protocolRealtimeDataAdapters) {
			this.protocolRealtimeDataAdapterMap.put(protocolRealtimeDataAdapter.getDataProtocol(), protocolRealtimeDataAdapter);
		}
	}

	public void setDefaultProtocolRealtimeDataAdapter(ProtocolRealtimeDataAdapterInter defaultProtocolRealtimeDataAdapter) {
		this.defaultProtocolRealtimeDataAdapter = defaultProtocolRealtimeDataAdapter;
	}

	public void setProtocolHistoryDataAdapters(List<ProtocolHistoryDataAdapterInter> protocolHistoryDataAdapters) {
		for (ProtocolHistoryDataAdapterInter protocolHistoryDataAdapter : protocolHistoryDataAdapters) {
			this.protocolHistoryDataAdapterMap.put(protocolHistoryDataAdapter.getDataProtocol(), protocolHistoryDataAdapter);
		}
	}

	public void setDefaultProtocolHistoryDataAdapter(ProtocolHistoryDataAdapterInter defaultProtocolHistoryDataAdapter) {
		this.defaultProtocolHistoryDataAdapter = defaultProtocolHistoryDataAdapter;
	}

	public JSONObject buildRealtimeDataSet(DataProtocolEnum dataProtocolEnum, List<Pollutant> pollutants, String rtdData, String statusData) {
		ProtocolRealtimeDataAdapterInter protocolRealtimeDataAdapter = protocolRealtimeDataAdapterMap.get(dataProtocolEnum);
		if (protocolRealtimeDataAdapter == null) {
			protocolRealtimeDataAdapter = defaultProtocolRealtimeDataAdapter;
		}
		return protocolRealtimeDataAdapter.buildDataSet(pollutants, rtdData, statusData);
	}

	public JSONObject buildHistoryDataSet(DataProtocolEnum dataProtocolEnum, List<Pollutant> pollutants, String data) {
		ProtocolHistoryDataAdapterInter protocolHistoryDataAdapter = protocolHistoryDataAdapterMap.get(dataProtocolEnum);
		if (protocolHistoryDataAdapter == null) {
			protocolHistoryDataAdapter = defaultProtocolHistoryDataAdapter;
		}
		return protocolHistoryDataAdapter.buildDataSet(pollutants, data);
	}
}
