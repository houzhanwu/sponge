package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.AuthModule;
import com.apr7.sponge.model.AuthModuleGroup;
import com.apr7.sponge.model.vo.ModuleGroupVO;
import com.apr7.sponge.service.AuthModuleService;

@Controller
@RequestMapping("/module")
public class AuthModuleController {
	@Autowired
	private AuthModuleService authModuleService;

	@RequestMapping("/listByGroup")
	@ResponseBody
	public List<ModuleGroupVO> listByGroup() {
		List<AuthModule> authModules = authModuleService.listAllModule();
		List<AuthModuleGroup> authModuleGroups = authModuleService.listAllModuleGroup();
		Map<Long, List<AuthModule>> moduleGroupMap = new HashMap<>();
		for (AuthModule authModule : authModules) {
			List<AuthModule> list = moduleGroupMap.get(authModule.getGroupId());
			if (list == null) {
				list = new ArrayList<>(1);
				moduleGroupMap.put(authModule.getGroupId(), list);
			}
			list.add(authModule);
		}
		List<ModuleGroupVO> moduleGroupVOs = new ArrayList<>();
		for (AuthModuleGroup authModuleGroup : authModuleGroups) {
			List<AuthModule> list = moduleGroupMap.get(authModuleGroup.getId());
			if (CollectionUtils.isNotEmpty(list)) {
				ModuleGroupVO moduleGroupVO = new ModuleGroupVO();
				moduleGroupVO.setGroupName(authModuleGroup.getName());
				Collections.sort(list, (o1, o2) -> {
					return o1.getOrder() - o2.getOrder();
				});
				moduleGroupVO.setModules(list);
				moduleGroupVOs.add(moduleGroupVO);
			}
		}
		return moduleGroupVOs;
	}
}