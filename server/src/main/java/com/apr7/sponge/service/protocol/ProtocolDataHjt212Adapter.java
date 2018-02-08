package com.apr7.sponge.service.protocol;

import java.util.List;

import org.apache.commons.collections4.MapUtils;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;

public class ProtocolDataHjt212Adapter implements ProtocolDataAdapterInter {

	@Override
	public JSONObject buildDataSet(List<Pollutant> pollutants, String rtdData, String statusData) {
		JSONObject dataSet = new JSONObject();
		JSONObject rtdJson = JSONObject.parseObject(rtdData);
		JSONObject statusJson = JSONObject.parseObject(statusData);
		for (Pollutant pollutant : pollutants) {
			String value = null;
			switch (pollutant.getType()) {
			case RTD:
				if (MapUtils.isNotEmpty(rtdJson)) {
					value = rtdJson.getString(pollutant.getMapping().getFieldKeyHjt212());
				}
				break;
			case STATUS:
				if (MapUtils.isNotEmpty(statusJson)) {
					value = statusJson.getString(pollutant.getMapping().getFieldKeyHjt212());
				}
				break;
			}
			dataSet.put("_" + pollutant.getId(), value);
		}
		return dataSet;
	}

	@Override
	public DataProtocolEnum getDataProtocol() {
		return DataProtocolEnum.HJT212;
	}

}
