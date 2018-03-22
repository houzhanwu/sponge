package com.apr7.sponge.protocol.knt2014.netty;

import java.util.List;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apr7.sponge.protocol.knt2014.CommandModel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class CommandModelDecoder extends MessageToMessageDecoder<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandModelDecoder.class);

	private CommandModel cmd;

	@Override
	protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
		CommandModel cmd = CommandModel.decode(msg);
		int packageSize = MapUtils.getIntValue(cmd.getCpValuesMap(), "PackageSize");
		int packageNum = MapUtils.getIntValue(cmd.getCpValuesMap(), "PackageNum");
		if (packageSize == 0 || packageSize == 1 || packageSize == packageNum) {
			out.add(cmd);
			this.cmd = null;
		} else if (packageNum == 1) {
			this.cmd = cmd;
		} else {
			this.cmd.setCp(cmd.getCp() + ";" + this.cmd.getCp());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.toString(), cause);
		ctx.close();
	}
}
