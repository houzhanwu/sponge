package com.apr7.sponge.protocol.knt2014.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apr7.sponge.protocol.knt2014.CommandModel;
import com.apr7.sponge.protocol.knt2014.server.command.Knt2014CommandHandler;
import com.apr7.sponge.protocol.knt2014.server.command.Knt2014CommandHandlerFactory;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

	private Knt2014CommandHandlerFactory commandHandlerFactory;

	public void setCommandHandlerFactory(Knt2014CommandHandlerFactory commandHandlerFactory) {
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
		// String crc16 = request.substring(6 + dataLength);
		// if (!StringUtils.equalsIgnoreCase(crc16, CRC16.checkout(dataString)))
		// {
		// LOGGER.error("error crc checkout: {}", request);
		// return;
		// }
		CommandModel cmd = CommandModel.create(dataString);
		Knt2014CommandHandler handler = commandHandlerFactory.getHandler(cmd);
		if (handler == null) {
			LOGGER.error("command not support: {}", request);
			return;
		}
		LOGGER.debug("execute command: {}", request);
		handler.doProcess(ctx, cmd);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
		ctx.channel().close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.toString(), cause);
		ctx.close();
	}

}
