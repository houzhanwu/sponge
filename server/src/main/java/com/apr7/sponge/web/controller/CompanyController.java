package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.constants.CompanyStatusEnum;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@RequestMapping("/status/listall")
	@ResponseBody
	public List<JSONObject> listAllStatus(HttpServletResponse response) {
		List<JSONObject> value = new ArrayList<>();
		for (CompanyStatusEnum companyStatusEnum : CompanyStatusEnum.values()) {
			JSONObject o = new JSONObject();
			o.put("code", companyStatusEnum.getCode());
			o.put("title", companyStatusEnum.getTitle());
			value.add(o);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		return value;
	}
}