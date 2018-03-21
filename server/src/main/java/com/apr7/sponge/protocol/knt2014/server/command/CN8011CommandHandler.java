package com.apr7.sponge.protocol.knt2014.server.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.protocol.knt2014.CommandModel;
import com.apr7.sponge.service.PollutantService;
import com.apr7.sponge.service.WorkshopService;

import io.netty.channel.ChannelHandlerContext;

public class CN8011CommandHandler implements Knt2014CommandHandler {

	public static final String CN = "8011";

	@Autowired
	private PollutantService pollutantService;

	@Autowired
	private WorkshopService workshopService;

	@Override
	public void doProcess(ChannelHandlerContext ctx, CommandModel cmd) {
		Long workshopId = workshopService.getWorkshopIdByDeviceMn(cmd.getMn());
		if (workshopId == null) {
			doResponse(ctx, cmd);
			return;
		}
		Map<String, String> cpValuesMap = cmd.getCpValuesMap();
		Set<String> fieldKeysSet = new HashSet<>();
		for (Map.Entry<String, String> entry : cpValuesMap.entrySet()) {
			if (entry.getKey().endsWith("-Flag")) {
				fieldKeysSet.add(entry.getKey().replace("-Flag", "").replace("-RS", ""));
			}
		}
		List<Long> pollutantIds = pollutantService.listPollutantIdsByFieldKeys(DataProtocolEnum.KNT2014, new ArrayList<>(fieldKeysSet));
		workshopService.addWorkshopPollutantMappings(workshopId, pollutantIds);
		doResponse(ctx, cmd);
	}

	private void doResponse(ChannelHandlerContext ctx, CommandModel cmd) {
		CommandModel res = cmd.createResCommand();
		res.setSt("91");
		res.setCn("8012");
		res.setCpValue("Login", "1");
		ctx.channel().writeAndFlush(res);
	}

	@Override
	public String getCn() {
		return CN;
	}

}
