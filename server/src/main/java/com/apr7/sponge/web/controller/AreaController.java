package com.apr7.sponge.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.Area;
import com.apr7.sponge.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaService areaService;

	@RequestMapping("/listall")
	@ResponseBody
	public List<Area> listAllArea() {
		List<Area> areas = areaService.listAllArea();
		return areas;
	}
}