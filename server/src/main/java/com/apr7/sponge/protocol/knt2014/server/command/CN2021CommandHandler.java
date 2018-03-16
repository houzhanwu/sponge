package com.apr7.sponge.protocol.knt2014.server.command;

import com.apr7.sponge.protocol.knt2014.CommandModel;

import io.netty.channel.ChannelHandlerContext;

public class CN2021CommandHandler implements Knt2014CommandHandler {

	public static final String CN = "2021";

	@Override
	public void doProcess(ChannelHandlerContext ctx, CommandModel cmd) {
		CommandModel res = cmd.createResCommand();
		res.setCn("9014");
		res.setCpValue("QN", res.getQn());
		res.setCpValue("CN", CN);
		ctx.channel().writeAndFlush(res);
	}

	@Override
	public String getCn() {
		return CN;
	}

}
