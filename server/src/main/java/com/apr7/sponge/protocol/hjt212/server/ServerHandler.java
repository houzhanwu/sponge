package com.apr7.sponge.protocol.hjt212.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apr7.sponge.protocol.hjt212.client.command.CommandModel;
import com.apr7.sponge.protocol.hjt212.server.command.Hjt212CommandHandler;
import com.apr7.sponge.protocol.hjt212.server.command.Hjt212CommandHandlerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

	private Hjt212CommandHandlerFactory commandHandlerFactory;

	public void setCommandHandlerFactory(Hjt212CommandHandlerFactory commandHandlerFactory) {
		this.commandHandlerFactory = commandHandlerFactory;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String request = (String) msg;
		if (!request.startsWith("##")) {
			LOGGER.error("error command: {}", request);
			return;
		}
		int dataLength = Integer.parseInt(request.substring(2, 6));
		String dataString = request.substring(6, 6 + dataLength);
//		String crc16 = request.substring(6 + dataLength);
//		if (!StringUtils.equalsIgnoreCase(crc16, CRC16.checkout(dataString))) {
//			LOGGER.error("error crc checkout: {}", request);
//			return;
//		}
		CommandModel cmd = CommandModel.create(dataString);
		Hjt212CommandHandler handler = commandHandlerFactory.getHandler(cmd);
		if (handler == null) {
			LOGGER.error("command not support: {}", request);
			return;
		}
		LOGGER.debug("execute command: {}", request);
		handler.doProcess(ctx, cmd);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.toString(), cause);
		ctx.close();
	}

}
