package com.apr7.sponge.protocol.knt2014.server.command;

import com.apr7.sponge.protocol.knt2014.CommandModel;

import io.netty.channel.ChannelHandlerContext;

public interface Knt2014CommandHandler {

	void doProcess(ChannelHandlerContext ctx, CommandModel cmd);

	String getCn();
}
