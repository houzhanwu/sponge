package com.apr7.sponge.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.vo.BaseInfoVO;
import com.apr7.sponge.service.AreaService;

@Controller
@RequestMapping("/baseinfo")
public class BaseInfoController {
	@Autowired
	private AreaService areaService;

	@RequestMapping("/get")
	@ResponseBody
	public BaseInfoVO getBaseInfo() {
		BaseInfoVO baseInfoVO = new BaseInfoVO();
		baseInfoVO.setAreas(areaService.listAllArea());
		return baseInfoVO;
	}
}