package com.apr7.sponge.protocol.knt2014.server.command;

import com.apr7.sponge.protocol.knt2014.CommandModel;

import io.netty.channel.ChannelHandlerContext;

public class CN8011CommandHandler implements Knt2014CommandHandler {

	public static final String CN = "8011";

	@Override
	public void doProcess(ChannelHandlerContext ctx, CommandModel cmd) {
		CommandModel res = cmd.createResCommand();
		res.setSt("91");
		res.setCn("8012");
		res.setCpValue("Login", "1");
		ctx.channel().writeAndFlush(res);
	}

	@Override
	public String getCn() {
		return CN;
	}

}
