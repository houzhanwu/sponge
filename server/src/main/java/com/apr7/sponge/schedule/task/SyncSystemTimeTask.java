package com.apr7.sponge.schedule.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.Device;
import com.apr7.sponge.service.DeviceService;

public class SyncSystemTimeTask extends BaseSpongeTask {

	@Autowired
	private DeviceService deviceService;

	@Override
	public void doAction() {
		List<Device> devices = deviceService.listActiveDevice();
		for (Device device : devices) {
			DataProtocolEnum dataProtocol = DataProtocolEnum.fromCode(device.getDataProtocol());
			if (dataProtocol == null) {
				continue;
			}
			switch (dataProtocol) {
			case HJT212:
				break;
			case KNT2014:
				new com.apr7.sponge.protocol.knt2014.client.command.CN1012ClientCommand(device).execute();
				break;
			default:
				break;
			}
		}
	}
}
