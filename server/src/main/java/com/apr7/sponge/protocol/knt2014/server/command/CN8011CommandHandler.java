package com.apr7.sponge.protocol.knt2014.server.command;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.apr7.sponge.protocol.knt2014.CommandModel;

import io.netty.channel.ChannelHandlerContext;

public class CN8011CommandHandler implements Knt2014CommandHandler {

	public static final String CN = "8011";

	@Override
	public void doProcess(ChannelHandlerContext ctx, CommandModel cmd) {
		StringBuilder d = new StringBuilder();
		d.append("QN=");
		d.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmsszzz"));
		d.append(";");
		d.append("ST=");
		d.append(cmd.getSt());
		d.append(";CN=8012;PW=123456;MN=66666660000111;Flag=0;CP=&&Login=1&&");
		ctx.write(new StringBuilder().append("##0084QN=20141226172716001;ST=91;CN=8012;PW=123456;MN=66666660000111;Flag=0;CP=&&Login=1&&D641\r\n"));
	}

	@Override
	public String getCn() {
		return CN;
	}

}
