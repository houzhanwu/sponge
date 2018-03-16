package com.apr7.sponge.protocol.knt2014.netty;

import java.util.List;

import com.apr7.sponge.protocol.knt2014.CommandModel;
import com.apr7.sponge.utils.CRC16;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.string.LineSeparator;
import io.netty.util.CharsetUtil;

public class CommandModelEncoder extends MessageToMessageEncoder<CommandModel> {

	@Override
	protected void encode(ChannelHandlerContext ctx, CommandModel msg, List<Object> out) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("QN=").append(msg.getQn()).append(';');
		sb.append("ST=").append(msg.getSt()).append(';');
		sb.append("CN=").append(msg.getCn()).append(';');
		sb.append("PW=").append(msg.getPw()).append(';');
		sb.append("MN=").append(msg.getMn()).append(';');
		sb.append("Flag=").append(msg.getFlag()).append(';');
		sb.append("CP=").append("&&").append(msg.makeCpString()).append("&&");
		String dataString = sb.toString();
		byte[] b = dataString.getBytes(CharsetUtil.UTF_8);
		ByteBuf buffer = ctx.alloc().buffer(b.length + 12);
		buffer.writeCharSequence("##", CharsetUtil.UTF_8);
		buffer.writeCharSequence(String.format("%04d", b.length), CharsetUtil.UTF_8);
		buffer.writeBytes(b);
		buffer.writeCharSequence(CRC16.checkout(dataString), CharsetUtil.UTF_8);
		buffer.writeCharSequence(LineSeparator.WINDOWS.value(), CharsetUtil.UTF_8);
		out.add(buffer);
	}
}
