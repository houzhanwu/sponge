package com.apr7.sponge.protocol.hjt212.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apr7.sponge.protocol.hjt212.server.command.Hjt212Command;
import com.apr7.sponge.protocol.hjt212.server.command.Hjt212CommandFactory;
import com.apr7.sponge.utils.CRC16;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

	private Hjt212CommandFactory hjt212Commands = new Hjt212CommandFactory();

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String request = (String) msg;
		if (!request.startsWith("##")) {
			LOGGER.error("error command: {}", request);
			return;
		}
		int dataLength = Integer.parseInt(request.substring(2, 6));
		String dataString = request.substring(6, 6 + dataLength);
		String crc16 = request.substring(6 + dataLength);
		if (!StringUtils.equalsIgnoreCase(crc16, CRC16.checkout(dataString))) {
			LOGGER.error("error crc checkout: {}", request);
			return;
		}
		Hjt212Command command;
		try {
			command = hjt212Commands.getCommand(dataString);
		} catch (RuntimeException e) {
			LOGGER.error("create command error: {}", e.getMessage(), e);
			return;
		}
		if (command == null) {
			LOGGER.error("command not support: {}", request);
			return;
		}
		LOGGER.debug("execute command: {}", request);
		command.doProcess(ctx);
		// TODO
		ctx.writeAndFlush("888");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.toString(), cause);
		ctx.close();
	}

}
