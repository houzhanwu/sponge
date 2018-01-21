package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.param.PollutantParam;
import com.apr7.sponge.model.vo.PollutantSimpleVO;
import com.apr7.sponge.model.vo.PollutantVO;
import com.apr7.sponge.service.PollutantService;

@Controller
@RequestMapping("/pollutant")
public class PollutantController {
	@Autowired
	private PollutantService pollutantService;

	@RequestMapping("/add")
	@ResponseBody
	public void addPollutant(PollutantParam pollutantParam) {
		Pollutant pollutant = pollutantParam.toModel();
		pollutantService.addPollutant(pollutant);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void deletePollutant(Long pollutantId) {
		pollutantService.deletePollutant(pollutantId);
	}

	@RequestMapping("/listall")
	@ResponseBody
	public List<PollutantVO> listShowPollutant() {
		List<Pollutant> pollutants = pollutantService.listAllPollutant();
		List<PollutantVO> pollutantVOs = new ArrayList<>(pollutants.size());
		for (Pollutant pollutant : pollutants) {
			pollutantVOs.add(PollutantVO.build(pollutant));
		}
		return pollutantVOs;
	}

	@RequestMapping("/listshow")
	@ResponseBody
	public List<PollutantSimpleVO> listAllPollutant() {
		List<Pollutant> pollutants = pollutantService.listShowingPollutant();
		List<PollutantSimpleVO> pollutantSimpleVOs = new ArrayList<>(pollutants.size());
		for (Pollutant pollutant : pollutants) {
			pollutantSimpleVOs.add(PollutantSimpleVO.build(pollutant));
		}
		return pollutantSimpleVOs;
	}
}