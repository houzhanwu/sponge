package com.apr7.sponge.web.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.DataProtocolEnum;
import com.apr7.sponge.model.HistoryData;
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.Workshop;
import com.apr7.sponge.model.vo.HistoryDataVO;
import com.apr7.sponge.service.HistoryDataService;
import com.apr7.sponge.service.PollutantService;
import com.apr7.sponge.service.WorkshopService;
import com.apr7.sponge.service.protocol.ProtocolDataService;
import com.apr7.sponge.utils.DateUtilsX;
import com.apr7.sponge.web.utils.ValidateUtils;

@Controller
@RequestMapping("/data")
public class HistoryDataController {
	@Autowired
	private HistoryDataService historyDataService;
	@Autowired
	private PollutantService pollutantService;
	@Autowired
	private WorkshopService workshopService;
	@Autowired
	private ProtocolDataService protocolDataService;

	@RequestMapping("/history")
	@ResponseBody
	public JSONObject history(Long companyId, Long workshopId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime, int page) {
		ValidateUtils.notNull(startTime, "开始时间不能为空");
		ValidateUtils.notNull(endTime, "结束时间不能为空");
		final int dataFrequencyMins = 5;
		List<Workshop> workshops;
		if (workshopId != null) {
			workshops = Arrays.asList(workshopService.getWorkshop(workshopId));
		} else {
			workshops = workshopService.listWorkshopNameByCompanyId(companyId);
			Collections.sort(workshops, (o1, o2) -> {
				return o2.getId().compareTo(o1.getId());
			});
		}
		if (workshops.isEmpty()) {
			JSONObject value = new JSONObject();
			value.put("total", 0);
			value.put("data", new ArrayList<HistoryDataVO>());
			return value;
		}
		int multiple = (int) Math.ceil(30D / workshops.size());
		int size = multiple * workshops.size();
		Date pageEndTime = DateUtilsX.floor(DateUtils.addMinutes(endTime, (page - 1) * multiple * -dataFrequencyMins), TimeUnit.MINUTES, dataFrequencyMins);
		Date pageStartTime = DateUtils.addMinutes(pageEndTime, multiple * -dataFrequencyMins);
		if (!pageStartTime.after(startTime)) {
			pageStartTime = DateUtils.addMinutes(DateUtilsX.ceil(startTime, TimeUnit.MINUTES, dataFrequencyMins), -dataFrequencyMins);
		}
		List<HistoryData> historyDatas = historyDataService.listHistoryDataByCompanyId(companyId, workshopId, pageStartTime, pageEndTime);
		List<Pollutant> pollutants = pollutantService.listShowingPollutant();
		List<HistoryDataVO> data = new ArrayList<>(size);
		Iterator<HistoryData> it = historyDatas.iterator();
		HistoryData historyData = null;
		if (it.hasNext()) {
			historyData = it.next();
		}
		for (Date currentTime = pageEndTime; currentTime.after(pageStartTime); currentTime = DateUtils.addMinutes(currentTime, -dataFrequencyMins)) {
			for (Workshop workshop : workshops) {
				JSONObject dataSet;
				if (historyData != null && currentTime.equals(DateUtilsX.floor(historyData.getDateTime(), TimeUnit.MINUTES, dataFrequencyMins))
						&& workshop.getId().equals(historyData.getWorkshopId())) {
					DataProtocolEnum dataProtocolEnum = DataProtocolEnum.fromCode(historyData.getDataProtocol());
					dataSet = protocolDataService.buildHistoryDataSet(dataProtocolEnum, pollutants, historyData.getData());
					if (it.hasNext()) {
						historyData = it.next();
					} else {
						historyData = null;
					}
				} else {
					dataSet = protocolDataService.buildHistoryDataSet(null, pollutants, null);
				}
				HistoryDataVO historyDataVO = new HistoryDataVO();
				historyDataVO.setWorkshopName(workshop.getName());
				historyDataVO.setDateTime(currentTime);
				historyDataVO.setDataSet(dataSet);
				data.add(historyDataVO);
			}
		}
		JSONObject value = new JSONObject();
		value.put("total", (DateUtilsX.floor(endTime, TimeUnit.MINUTES, dataFrequencyMins).getTime()
				- DateUtilsX.ceil(startTime, TimeUnit.MINUTES, dataFrequencyMins).getTime() + TimeUnit.MINUTES.toMillis(dataFrequencyMins)) / TimeUnit.MINUTES.toMillis(dataFrequencyMins) * workshops.size());
		value.put("data", data);
		return value;
	}

	@RequestMapping("/history/export")
	@ResponseBody
	public void export(Long companyId, Long workshopId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime, HttpServletResponse response) throws IOException {
		ValidateUtils.notNull(startTime, "开始时间不能为空");
		ValidateUtils.notNull(endTime, "结束时间不能为空");
		final int dataFrequencyMins = 5;
		List<Workshop> workshops;
		if (workshopId != null) {
			workshops = Arrays.asList(workshopService.getWorkshop(workshopId));
		} else {
			workshops = workshopService.listWorkshopNameByCompanyId(companyId);
			Collections.sort(workshops, (o1, o2) -> {
				return o2.getId().compareTo(o1.getId());
			});
		}
		Date pageStartTime = DateUtils.addMinutes(DateUtilsX.ceil(startTime, TimeUnit.MINUTES, dataFrequencyMins), -dataFrequencyMins);
		Date pageEndTime = DateUtilsX.floor(endTime, TimeUnit.MINUTES, dataFrequencyMins);
		List<HistoryData> historyDatas = historyDataService.listHistoryDataByCompanyId(companyId, workshopId, pageStartTime, pageEndTime);
		List<Pollutant> pollutants = pollutantService.listShowingPollutant();
		Iterator<HistoryData> it = historyDatas.iterator();
		HistoryData historyData = null;
		if (it.hasNext()) {
			historyData = it.next();
		}
		final String fileName = "历史数据_" + DateFormatUtils.format(startTime, "yyyyMMdd-HHmm") + "至" + DateFormatUtils.format(endTime, "yyyyMMdd-HHmm") + ".xlsx";
		try (Workbook workbook = new SXSSFWorkbook(500_000)) {
			Sheet sheet = workbook.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("车间");
			row.createCell(1).setCellValue("记录时间");
			for (int i = 0; i < pollutants.size(); i++) {
				Pollutant pollutant = pollutants.get(i);
				row.createCell(i + 2).setCellValue(pollutant.getName());
			}
			if (!workshops.isEmpty()) {
				int index = 1;
				for (Date currentTime = pageEndTime; currentTime.after(pageStartTime); currentTime = DateUtils.addMinutes(currentTime, -dataFrequencyMins)) {
					for (Workshop workshop : workshops) {
						row = sheet.createRow(index);
						JSONObject dataSet;
						if (historyData != null && currentTime.equals(DateUtilsX.floor(historyData.getDateTime(), TimeUnit.MINUTES, dataFrequencyMins))
								&& workshop.getId().equals(historyData.getWorkshopId())) {
							DataProtocolEnum dataProtocolEnum = DataProtocolEnum.fromCode(historyData.getDataProtocol());
							dataSet = protocolDataService.buildHistoryDataSet(dataProtocolEnum, pollutants, historyData.getData());
							if (it.hasNext()) {
								historyData = it.next();
							} else {
								historyData = null;
							}
						} else {
							dataSet = protocolDataService.buildHistoryDataSet(null, pollutants, null);
						}
						row.createCell(0).setCellValue(workshop.getName());
						row.createCell(1).setCellValue(DateFormatUtils.format(currentTime, "yyyy-MM-dd HH:mm:ss"));
						for (int i = 0; i < pollutants.size(); i++) {
							Pollutant pollutant = pollutants.get(i);
							row.createCell(i + 2).setCellValue(StringUtils.defaultIfEmpty(dataSet.getString("_" + pollutant.getId()), "-"));
						}
						index++;
					}
				}
			}
			response.reset();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			ServletOutputStream os = response.getOutputStream();
			workbook.write(os);
		}
	}
}