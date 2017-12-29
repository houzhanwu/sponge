package com.apr7.sponge.protocol.hjt212.server.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apr7.sponge.protocol.hjt212.client.command.CommandModel;

public class Hjt212CommandHandlerFactory {

	private Map<String, Hjt212CommandHandler> _handlerMap = new HashMap<>();

	public void setHandlers(List<Hjt212CommandHandler> handlers) {
		for (Hjt212CommandHandler handler : handlers) {
			_handlerMap.put(handler.getCn(), handler);
		}
	}

	public Hjt212CommandHandler getHandler(CommandModel cmd) {
		Hjt212CommandHandler handler = _handlerMap.get(cmd.getCn());
		return handler;
	}
}
