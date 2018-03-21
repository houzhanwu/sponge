package com.apr7.sponge.protocol.knt2014.server.command;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.protocol.knt2014.CommandModel;
import com.apr7.sponge.service.RealTimeDataService;
import com.apr7.sponge.service.WorkshopService;

import io.netty.channel.ChannelHandlerContext;

public class CN2021CommandHandler implements Knt2014CommandHandler {

	public static final String CN = "2021";

	@Autowired
	private WorkshopService workshopService;

	@Autowired
	private RealTimeDataService realTimeDataService;

	@Override
	public void doProcess(ChannelHandlerContext ctx, CommandModel cmd) {
		Long workshopId = workshopService.getWorkshopIdByDeviceMn(cmd.getMn());
		if (workshopId == null) {
			doResponse(ctx, cmd);
			return;
		}
		Date lmodify;
		try {
			lmodify = DateUtils.parseDate(cmd.getCpValue("DataTime"), "yyyyMMddHHmmss", "yyyyMMddHHmmssSSS");
		} catch (ParseException e) {
			lmodify = new Date();
		}
		realTimeDataService.saveRealTimeStatus(workshopId, cmd.getCp(), DataProtocolEnum.KNT2014, lmodify);
		doResponse(ctx, cmd);
	}

	private void doResponse(ChannelHandlerContext ctx, CommandModel cmd) {
		CommandModel res = cmd.createResCommand();
		res.setCn("9014");
		res.setCpValue("QN", res.getQn());
		res.setCpValue("CN", CN);
		ctx.channel().writeAndFlush(res);
	}

	@Override
	public String getCn() {
		return CN;
	}

}
