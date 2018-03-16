package com.apr7.sponge.protocol.knt2014.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apr7.sponge.protocol.knt2014.CommandModel;
import com.apr7.sponge.protocol.knt2014.server.command.Knt2014CommandHandler;
import com.apr7.sponge.protocol.knt2014.server.command.Knt2014CommandHandlerFactory;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<CommandModel> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

	private Knt2014CommandHandlerFactory commandHandlerFactory;

	public void setCommandHandlerFactory(Knt2014CommandHandlerFactory commandHandlerFactory) {
		this.commandHandlerFactory = commandHandlerFactory;
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, CommandModel msg) throws Exception {
		Knt2014CommandHandler handler = commandHandlerFactory.getHandler(msg);
		if (handler == null) {
			LOGGER.error("command not support: {}", msg.getRaw());
			return;
		}
		LOGGER.debug("execute command: {}", msg.getRaw());
		handler.doProcess(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.toString(), cause);
		ctx.close();
	}
}
