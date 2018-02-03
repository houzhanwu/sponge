package com.apr7.sponge.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.AuthModule;
import com.apr7.sponge.model.AuthRole;
import com.apr7.sponge.model.param.AuthRoleParam;
import com.apr7.sponge.service.AuthRoleService;

@Controller
@RequestMapping("/role")
public class AuthRoleController {
	@Autowired
	private AuthRoleService authRoleService;

	@RequestMapping("/add")
	@ResponseBody
	public void addRole(AuthRoleParam authRoleParam) {
		AuthRole authRole = authRoleParam.toModel();
		authRoleService.addRole(authRole);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void deleteRole(Long roleId) {
		authRoleService.deleteRole(roleId);
	}

	@RequestMapping("/update")
	@ResponseBody
	public void updateRole(AuthRoleParam authRoleParam) {
		AuthRole authRole = authRoleParam.toModel();
		authRoleService.updateRole(authRole);
	}

	@RequestMapping("/listall")
	@ResponseBody
	public List<AuthRole> listAllRole() {
		List<AuthRole> authRoles = authRoleService.listAllRole();
		return authRoles;
	}

	@RequestMapping("/listModule")
	@ResponseBody
	public List<AuthModule> listModule(Long roleId) {
		List<AuthModule> authModules = authRoleService.listModules(roleId);
		return authModules;
	}
}