package com.apr7.sponge.protocol.knt2014.client.command;

import java.io.IOException;

import com.apr7.sponge.model.Device;
import com.apr7.sponge.protocol.knt2014.CommandModel;
import com.apr7.sponge.protocol.knt2014.client.ClientSession;

public class CN1012ClientCommand extends Knt2014ClientCommand {

	private static final String CN = "1012";

	private Device device;

	public CN1012ClientCommand(Device device) {
		this.device = device;
	}

	public boolean execute() {
		try (ClientSession clientSession = new ClientSession(device.getIp(), device.getPort())) {
			CommandModel cmd = CommandModel.createCommand(CN);
			cmd.setMn(device.getMn());
			cmd.setCpValue("SystemTime", cmd.getQn());
			clientSession.send(cmd);
			CommandModel res = clientSession.readCommand();
			if (res == null) {
				return false;
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		Device device = new Device();
		device.setMn("66666660000111");
		device.setIp("127.0.0.1");
		device.setPort(10002);
		CN1012ClientCommand command = new CN1012ClientCommand(device);
		command.execute();
	}
}
