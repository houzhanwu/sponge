package com.apr7.sponge.service.protocol;

import java.util.List;

import org.apache.commons.collections4.MapUtils;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;

public class ProtocolHistoryDataHjt212Adapter implements ProtocolHistoryDataAdapterInter {

	@Override
	public JSONObject buildDataSet(List<Pollutant> pollutants, String data) {
		JSONObject dataSet = new JSONObject();
		JSONObject dataJson = JSONObject.parseObject(data);
		for (Pollutant pollutant : pollutants) {
			String value = null;
			switch (pollutant.getType()) {
			case RTD:
				if (MapUtils.isNotEmpty(dataJson)) {
					value = dataJson.getString(pollutant.getMapping().getFieldKeyHjt212());
				}
				break;
			case STATUS:
				if (MapUtils.isNotEmpty(dataJson)) {
					value = dataJson.getString(pollutant.getMapping().getFieldKeyHjt212());
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
