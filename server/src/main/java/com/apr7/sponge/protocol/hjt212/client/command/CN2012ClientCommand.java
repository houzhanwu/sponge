package com.apr7.sponge.protocol.hjt212.client.command;

import java.io.IOException;

import com.apr7.sponge.protocol.hjt212.client.ClientSession;
import com.apr7.sponge.protocol.hjt212.exception.Hjt212Exception;

public class CN2012ClientCommand extends Hjt212ClientCommand {

	public void execute() throws IOException {
		try (ClientSession clientSession = new ClientSession("localhost", 9001)) {
			CommandModel cmd = CommandModel.create("QN=20160801085857223;ST=32;CN=2012;PW=123456;MN=010000A8900016F000169DC0;Flag=5;CP=&&&&");
			clientSession.send(cmd);
			CommandModel res = clientSession.readCommand();
			if (res == null) {
				throw new Hjt212Exception();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		CN2012ClientCommand command = new CN2012ClientCommand();
		command.execute();
	}
}
