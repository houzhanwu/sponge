package com.apr7.sponge.protocol.knt2014.server.command;

import com.apr7.sponge.protocol.knt2014.CommandModel;

import io.netty.channel.ChannelHandlerContext;

public class CN2011CommandHandler implements Knt2014CommandHandler {

	public static final String CN = "2011";

	@Override
	public void doProcess(ChannelHandlerContext ctx, CommandModel cmd) {
		ctx.write(new StringBuilder().append("##0094QN=20160801085857223;ST=32;CN=9011;PW=123456;MN=010000A8900016F000169DC0;Flag=5;CP=&&QnRtn=1&&2a80\r\n"));
	}

	@Override
	public String getCn() {
		return CN;
	}

}
