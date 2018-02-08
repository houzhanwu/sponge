package com.apr7.sponge.service.protocol;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Pollutant;

public interface ProtocolDataAdapterInter {

	JSONObject buildDataSet(List<Pollutant> pollutants, String rtdData, String statusData);

	DataProtocolEnum getDataProtocol();
}
