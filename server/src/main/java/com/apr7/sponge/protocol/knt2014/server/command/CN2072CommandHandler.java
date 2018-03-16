package com.apr7.sponge.protocol.knt2014.server.command;

import com.apr7.sponge.protocol.knt2014.CommandModel;

import io.netty.channel.ChannelHandlerContext;

public class CN2072CommandHandler implements Knt2014CommandHandler {

	public static final String CN = "2072";

	@Override
	public void doProcess(ChannelHandlerContext ctx, CommandModel cmd) {
		ctx.close();
	}

	@Override
	public String getCn() {
		return CN;
	}

}
