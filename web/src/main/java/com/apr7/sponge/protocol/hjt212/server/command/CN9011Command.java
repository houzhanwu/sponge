package com.apr7.sponge.protocol.hjt212.server.command;

import org.apache.commons.lang3.StringUtils;

import com.apr7.sponge.protocol.hjt212.server.Constants;

import io.netty.channel.ChannelHandlerContext;

public class CN9011Command extends Hjt212Command {

	public static final String CN = "9011";

	@Override
	public void doProcess(ChannelHandlerContext ctx) {
		if (!this.isOk()) {
			ctx.close();
		}
	}

	private boolean isOk() {
		return StringUtils.equals(this.getCpValue(Constants.CpParamName.QN_RTN), "1");
	}
}
