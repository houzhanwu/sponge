package com.apr7.sponge.protocol.hjt212.client.command;

import java.io.IOException;

import com.apr7.sponge.protocol.hjt212.Constants.CpParamName;
import com.apr7.sponge.protocol.hjt212.client.ClientSession;
import com.apr7.sponge.protocol.hjt212.exception.ExeHjt212Exception;
import com.apr7.sponge.protocol.hjt212.exception.QnHjt212Exception;

public class CN2011ClientCommand extends Hjt212ClientCommand {

	public void execute() throws IOException {
		try (ClientSession clientSession = new ClientSession("localhost", 9001)) {
			CommandModel cmd = CommandModel.create("QN=20160801085857223;ST=32;CN=2011;PW=123456;MN=010000A8900016F000169DC0;Flag=5;CP=&&&&");
			clientSession.send(cmd);
			CommandModel res = clientSession.readCommand();
			int qnRtn = Integer.parseInt(res.getCpValue(CpParamName.QN_RTN));
			if (qnRtn != 1) {
				throw new QnHjt212Exception(qnRtn);
			}
			res = clientSession.readCommand();
			int exeRtn = Integer.parseInt(res.getCpValue(CpParamName.EXE_RTN));
			if (exeRtn != 1) {
				throw new ExeHjt212Exception(qnRtn);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		CN2011ClientCommand command = new CN2011ClientCommand();
		command.execute();
	}
}
