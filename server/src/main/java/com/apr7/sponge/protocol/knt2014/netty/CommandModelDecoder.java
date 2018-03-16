package com.apr7.sponge.protocol.knt2014.netty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apr7.sponge.protocol.knt2014.CommandModel;
import com.apr7.sponge.utils.CRC16;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class CommandModelDecoder extends MessageToMessageDecoder<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandModelDecoder.class);

	@Override
	protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
		String request = (String) msg;
		if (!request.startsWith("##")) {
			LOGGER.error("error command: {}", request);
			throw new IllegalArgumentException("error command.");
		}
		int dataLength = Integer.parseInt(request.substring(2, 6));
		String dataString = request.substring(6, 6 + dataLength);
		String crc16 = request.substring(6 + dataLength);
		if (!StringUtils.equalsIgnoreCase(crc16, CRC16.checkout(dataString))) {
			LOGGER.error("error crc checkout: {}", request);
			throw new IllegalArgumentException("error crc checkout.");
		}
		Map<String, String> cmdParam = new HashMap<>();
		String[] tokens = StringUtils.splitByWholeSeparator(dataString, ";CP=&&");
		String[] cmdParamtokens = StringUtils.split(tokens[0], ';');
		for (String token : cmdParamtokens) {
			String[] kv = StringUtils.split(token, '=');
			cmdParam.put(kv[0].toUpperCase(), kv[1]);
		}
		CommandModel cmd = new CommandModel();
		cmd.setRaw(request);
		cmd.setQn(cmdParam.get("QN"));
		cmd.setSt(cmdParam.get("ST"));
		cmd.setCn(cmdParam.get("CN"));
		cmd.setPw(cmdParam.get("PW"));
		cmd.setMn(cmdParam.get("MN"));
		cmd.setFlag(cmdParam.get("FLAG"));
		cmd.setSver(cmdParam.get("SVER"));
		cmd.setSvdata(cmdParam.get("SVDATA"));
		cmd.setCp(StringUtils.substring(tokens[1], 0, -2));
		out.add(cmd);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.toString(), cause);
		ctx.close();
	}
}
