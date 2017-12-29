package com.apr7.sponge.protocol.hjt212.server.command;

import com.apr7.sponge.protocol.hjt212.client.command.CommandModel;

import io.netty.channel.ChannelHandlerContext;

public interface Hjt212CommandHandler {

	void doProcess(ChannelHandlerContext ctx, CommandModel cmd);

	String getCn();
}
