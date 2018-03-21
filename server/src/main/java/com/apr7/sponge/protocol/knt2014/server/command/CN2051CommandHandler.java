package com.apr7.sponge.protocol.knt2014.server.command;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.HistoryData;
import com.apr7.sponge.model.Workshop;
import com.apr7.sponge.protocol.knt2014.CommandModel;
import com.apr7.sponge.service.HistoryDataService;
import com.apr7.sponge.service.WorkshopService;

import io.netty.channel.ChannelHandlerContext;

public class CN2051CommandHandler implements Knt2014CommandHandler {

	public static final String CN = "2051";

	@Autowired
	private WorkshopService workshopService;

	@Autowired
	private HistoryDataService historyDataService;

	@Override
	public void doProcess(ChannelHandlerContext ctx, CommandModel cmd) {
		Workshop workshop = workshopService.getWorkshopByDeviceMn(cmd.getMn());
		if (workshop == null) {
			doResponse(ctx, cmd);
			return;
		}
		HistoryData historyData = new HistoryData();
		historyData.setWorkshopId(workshop.getId());
		try {
			historyData.setDateTime(DateUtils.parseDate(cmd.getCpValue("DataTime"), "yyyyMMddHHmmss", "yyyyMMddHHmmssSSS"));
		} catch (ParseException e) {
			doResponse(ctx, cmd);
			return;
		}
		historyData.setCompanyId(workshop.getCompanyId());
		historyData.setData(cmd.getCp());
		historyData.setDataProtocol(DataProtocolEnum.KNT2014.getCode());
		historyDataService.addHistoryData(historyData);
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
