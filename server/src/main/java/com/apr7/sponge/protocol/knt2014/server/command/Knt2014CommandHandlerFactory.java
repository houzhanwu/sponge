package com.apr7.sponge.protocol.knt2014.server.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apr7.sponge.protocol.knt2014.CommandModel;

public class Knt2014CommandHandlerFactory {

	private Map<String, Knt2014CommandHandler> _handlerMap = new HashMap<>();

	public void setHandlers(List<Knt2014CommandHandler> handlers) {
		for (Knt2014CommandHandler handler : handlers) {
			_handlerMap.put(handler.getCn(), handler);
		}
	}

	public Knt2014CommandHandler getHandler(CommandModel cmd) {
		Knt2014CommandHandler handler = _handlerMap.get(cmd.getCn());
		return handler;
	}
}
