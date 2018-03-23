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
import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.param.CompanyParam;
import com.apr7.sponge.model.vo.CompanyVO;
import com.apr7.sponge.model.vo.PollutantSimpleVO;
import com.apr7.sponge.service.CompanyService;
import com.apr7.sponge.service.WorkshopService;
import com.apr7.sponge.utils.MultipageList;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private WorkshopService workshopService;

	@RequestMapping("/add")
	@ResponseBody
	public void addCompany(CompanyParam companyParam) {
		Company company = companyParam.toModel();
		companyService.addCompany(company);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void deleteCompany(Long companyId) {
		companyService.deleteCompany(companyId);
	}

	@RequestMapping("/update")
	@ResponseBody
	public void updateCompany(CompanyParam companyParam) {
		Company company = companyParam.toModel();
		companyService.updateCompany(company);
	}

	@RequestMapping("/get")
	@ResponseBody
	public Company getCompany(Long companyId) {
		Company company = companyService.getCompany(companyId);
		return company;
	}

	@RequestMapping("/list")
	@ResponseBody
	public MultipageList<CompanyVO> listCompany(String keyword, int page, int size) {
		MultipageList<Company> companys = companyService.listCompany(keyword, page, size);
		List<CompanyVO> companyVOs = new ArrayList<>(companys.getData().size());
		for (Company company : companys.getData()) {
			companyVOs.add(CompanyVO.build(company));
		}
		MultipageList<CompanyVO> multipageList = new MultipageList<>(companyVOs, companys.getTotal());
		return multipageList;
	}

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

	@RequestMapping("/pollutant/listshow")
	@ResponseBody
	public List<PollutantSimpleVO> listShowPollutant(Long companyId, Long workshopId) {
		List<Pollutant> pollutants;
		if (workshopId != null) {
			pollutants = workshopService.listPollutantByWorkshopId(workshopId);
		} else {
			pollutants = companyService.listPollutantByCompanyId(companyId);
		}
		List<PollutantSimpleVO> pollutantSimpleVOs = new ArrayList<>(pollutants.size());
		for (Pollutant pollutant : pollutants) {
			pollutantSimpleVOs.add(PollutantSimpleVO.build(pollutant));
		}
		return pollutantSimpleVOs;
	}
}