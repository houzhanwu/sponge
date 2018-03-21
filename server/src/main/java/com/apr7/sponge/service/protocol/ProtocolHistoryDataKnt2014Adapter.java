package com.apr7.sponge.service.protocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;

public class ProtocolHistoryDataKnt2014Adapter implements ProtocolHistoryDataAdapterInter {

	@Override
	public JSONObject buildDataSet(List<Pollutant> pollutants, String data) {
		JSONObject dataSet = new JSONObject();
		Map<String, String> dataMap = parseCp(data);
		for (Pollutant pollutant : pollutants) {
			String value;
			switch (pollutant.getType()) {
			case RTD:
				value = dataMap.get(pollutant.getMapping().getFieldKeyKnt2014() + "-Avg");
				break;
			case STATUS:
				value = dataMap.get(pollutant.getMapping().getFieldKeyKnt2014() + "-RS");
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

	private Map<String, String> parseCp(String cp) {
		Map<String, String> result = new HashMap<String, String>();
		String[] tokens = StringUtils.split(cp, ';');
		for (String token : tokens) {
			String[] fields = StringUtils.split(token, ',');
			for (String field : fields) {
				String[] kv = StringUtils.split(field, '=');
				result.put(kv[0], kv[1]);
			}
		}
		return result;
	}

}
