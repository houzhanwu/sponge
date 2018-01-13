package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.CompanyStatusEnum;
import com.apr7.sponge.model.Company;
import com.apr7.sponge.model.vo.CompanyWorkshopVO;
import com.apr7.sponge.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@RequestMapping("/status/listall")
	@ResponseBody
	public List<JSONObject> listAllStatus() {
		List<JSONObject> value = new ArrayList<>();
		for (CompanyStatusEnum companyStatusEnum : CompanyStatusEnum.values()) {
			JSONObject o = new JSONObject();
			o.put("code", companyStatusEnum.getCode());
			o.put("title", companyStatusEnum.getTitle());
			value.add(o);
		}
		return value;
	}

	@RequestMapping("/listall")
	@ResponseBody
	public List<Company> listAllCompany() {
		List<Company> companys = companyService.listAllCompany();
		return companys;
	}

	@RequestMapping("/listallCompanyWorkshop")
	@ResponseBody
	public List<CompanyWorkshopVO> listAllCompanyWorkshop() {
		List<CompanyWorkshopVO> companyWorkshopVOs = companyService.listAllCompanyWorkshopVO();
		return companyWorkshopVOs;
	}
}