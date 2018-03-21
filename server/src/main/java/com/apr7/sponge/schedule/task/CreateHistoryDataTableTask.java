package com.apr7.sponge.schedule.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.apr7.sponge.service.HistoryDataService;
import com.apr7.sponge.utils.DateUtilsX;

public class CreateHistoryDataTableTask extends BaseSpongeTask {

	@Autowired
	private HistoryDataService historyDataService;

	@Override
	public void doAction() {
		int year = DateUtilsX.getYear(new Date()) + 1;
		historyDataService.createHistoryTable(year);
	}
}
