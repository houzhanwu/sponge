package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.Workshop;
import com.apr7.sponge.model.param.WorkshopParam;
import com.apr7.sponge.model.vo.PollutantVO;
import com.apr7.sponge.model.vo.WorkshopVO;
import com.apr7.sponge.service.WorkshopService;

@Controller
@RequestMapping("/workshop")
public class WorkshopController {
	@Autowired
	private WorkshopService workshopService;

	@RequestMapping("/add")
	@ResponseBody
	public void addWorkshop(WorkshopParam workshopParam) {
		Workshop workshop = workshopParam.toModel();
		workshopService.addWorkshop(workshop, workshopParam.getDeviceMn());
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void deleteWorkshop(Long workshopId) {
		workshopService.deleteWorkshop(workshopId);
	}

	@RequestMapping("/update")
	@ResponseBody
	public void updateWorkshop(WorkshopParam workshopParam) {
		Workshop workshop = workshopParam.toModel();
		workshopService.updateWorkshop(workshop, workshopParam.getDeviceMn());
	}

	@RequestMapping("/get")
	@ResponseBody
	public WorkshopVO getWorkshop(Long workshopId) {
		Workshop workshop = workshopService.getWorkshop(workshopId);
		WorkshopVO workshopVO = WorkshopVO.build(workshop);
		String deviceMn = workshopService.getDeviceMnByWorkshopId(workshopId);
		workshopVO.setDeviceMn(deviceMn);
		return workshopVO;
	}

	@RequestMapping("/listAllByCompanyId")
	@ResponseBody
	public List<WorkshopVO> listAllWorkshop(Long companyId) {
		List<Workshop> workshops = workshopService.listWorkshopByCompanyId(companyId);
		List<WorkshopVO> workshopVOs = new ArrayList<WorkshopVO>(workshops.size());
		for (Workshop workshop : workshops) {
			workshopVOs.add(WorkshopVO.build(workshop));
		}
		return workshopVOs;
	}

	@RequestMapping("/pollutant/listall")
	@ResponseBody
	public List<PollutantVO> listAllPollutant(Long workshopId) {
		List<Pollutant> pollutants = workshopService.listPollutantByWorkshopId(workshopId);
		List<PollutantVO> pollutantVOs = new ArrayList<>(pollutants.size());
		for (Pollutant pollutant : pollutants) {
			pollutantVOs.add(PollutantVO.build(pollutant));
		}
		return pollutantVOs;
	}

	@RequestMapping("/pollutant/delete")
	@ResponseBody
	public void deletePollutant(Long workshopId, Long pollutantId) {
		workshopService.deletePollutantByWorkshopId(workshopId, pollutantId);
	}
}