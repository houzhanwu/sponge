package com.apr7.sponge.service.protocol;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;

public class ProtocolDataKnt2014Adapter implements ProtocolDataAdapterInter {

	@Override
	public JSONObject buildDataSet(List<Pollutant> pollutants, String rtdData, String statusData) {
		JSONObject dataSet = new JSONObject();
		JSONObject rtdJson = JSONObject.parseObject(rtdData);
		JSONObject statusJson = JSONObject.parseObject(statusData);
		for (Pollutant pollutant : pollutants) {
			String value;
			switch (pollutant.getType()) {
			case RTD:
				value = rtdJson.getString(pollutant.getMapping().getFieldKeyKnt2014());
				break;
			case STATUS:
				value = statusJson.getString(pollutant.getMapping().getFieldKeyKnt2014());
				break;
			default:
				value = null;
				break;
			}
			dataSet.put("_" + pollutant.getId(), value);
		}
		return dataSet;
	}

	@Override
	public DataProtocolEnum getDataProtocol() {
		return DataProtocolEnum.KNT2014;
	}

}
