package com.apr7.sponge.service.protocol;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;

public class ProtocolHistoryDataDefaultAdapter implements ProtocolHistoryDataAdapterInter {

	@Override
	public JSONObject buildDataSet(List<Pollutant> pollutants, String data) {
		JSONObject dataSet = new JSONObject();
		for (Pollutant pollutant : pollutants) {
			String value = null;
			dataSet.put("_" + pollutant.getId(), value);
		}
		return dataSet;
	}

	@Override
	public DataProtocolEnum getDataProtocol() {
		return null;
	}
}
