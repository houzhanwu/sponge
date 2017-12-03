package com.apr7.sponge.protocol.hjt212.client;

import java.nio.charset.Charset;

import com.apr7.sponge.protocol.hjt212.server.command.Hjt212Command;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CommandEncoder extends MessageToByteEncoder<Hjt212Command> {
	@Override
	protected void encode(ChannelHandlerContext ctx, Hjt212Command msg, ByteBuf out) {
		out.writeCharSequence(new StringBuilder().append("##0094QN=20160801085857223;ST=32;CN=9011;PW=123456;MN=010000A8900016F000169DC0;Flag=5;CP=&&QnRtn=1&&2a80\r\n"), Charset.forName("utf-8"));
	}
}
