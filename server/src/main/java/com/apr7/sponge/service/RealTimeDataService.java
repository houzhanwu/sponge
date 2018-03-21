package com.apr7.sponge.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.dao.RealTimeDataDao;
import com.apr7.sponge.model.RealTimeData;

@Service
public class RealTimeDataService {
	@Autowired
	private RealTimeDataDao realTimeDataDao;

	public void saveRealTimeRtd(Long workshopId, String rtdData, DataProtocolEnum dataProtocol, Date lmodify) {
		realTimeDataDao.saveRealTimeRtd(workshopId, rtdData, dataProtocol.getCode(), lmodify);
	}

	public void saveRealTimeStatus(Long workshopId, String statusData, DataProtocolEnum dataProtocol, Date lmodify) {
		realTimeDataDao.saveRealTimeStatus(workshopId, statusData, dataProtocol.getCode(), lmodify);
	}

	public List<RealTimeData> listAllRealTimeData() {
		return realTimeDataDao.listAllRealTimeData();
	}
}
