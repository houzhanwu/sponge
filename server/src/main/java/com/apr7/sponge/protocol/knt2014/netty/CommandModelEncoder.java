package com.apr7.sponge.protocol.knt2014.netty;

import java.util.List;

import com.apr7.sponge.protocol.knt2014.CommandModel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class CommandModelEncoder extends MessageToMessageEncoder<CommandModel> {

	@Override
	protected void encode(ChannelHandlerContext ctx, CommandModel msg, List<Object> out) throws Exception {
		out.add(CommandModel.encode(msg));
	}
}
