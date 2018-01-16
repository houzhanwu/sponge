package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.vo.PollutantVO;
import com.apr7.sponge.service.PollutantService;

@Controller
@RequestMapping("/pollutant")
public class PollutantController {
	@Autowired
	private PollutantService pollutantService;

	@RequestMapping("/listall")
	@ResponseBody
	public List<Pollutant> listShowPollutant() {
		List<Pollutant> pollutants = pollutantService.listAllPollutant();
		return pollutants;
	}

	@RequestMapping("/listshow")
	@ResponseBody
	public List<PollutantVO> listAllPollutant() {
		List<Pollutant> pollutants = pollutantService.listShowingPollutant();
		List<PollutantVO> pollutantVOs = new ArrayList<>(pollutants.size());
		for (Pollutant pollutant : pollutants) {
			pollutantVOs.add(PollutantVO.build(pollutant));
		}
		return pollutantVOs;
	}
}