package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.exception.SpongeException;
import com.apr7.sponge.model.AuthModule;
import com.apr7.sponge.model.AuthRole;
import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.model.param.AuthRoleParam;
import com.apr7.sponge.model.vo.SimpleUserVO;
import com.apr7.sponge.service.AuthModuleService;
import com.apr7.sponge.service.AuthRoleService;
import com.apr7.sponge.service.UserService;

@Controller
@RequestMapping("/role")
public class AuthRoleController {
	@Autowired
	private AuthRoleService authRoleService;
	@Autowired
	private AuthModuleService authModuleService;
	@Autowired
	private UserService userService;

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

	@RequestMapping("/updateModule")
	@ResponseBody
	public void updateModule(Long roleId, @RequestParam("moduleIds") List<Long> moduleIds) {
		if (moduleIds.size() != authModuleService.checkExists(moduleIds).size()) {
			throw new SpongeException("非法操作");
		}
		authRoleService.updateModules(roleId, moduleIds);
	}

	@RequestMapping("/listModule")
	@ResponseBody
	public List<AuthModule> listModule(Long roleId) {
		List<AuthModule> authModules = authRoleService.listModules(roleId);
		return authModules;
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public void addUser(Long roleId, String username) {
		Long userId;
		if ("admin".equals(username) || (userId = userService.getUserIdByUsername(username)) == null) {
			throw new SpongeException("该用户不存在，请核对");
		}
		authRoleService.addUser(roleId, userId);
	}

	@RequestMapping("/removeUser")
	@ResponseBody
	public void removeUser(Long roleId, String username) {
		Long userId;
		if ((userId = userService.getUserIdByUsername(username)) == null) {
			throw new SpongeException("该用户不存在，请核对");
		}
		authRoleService.removeUser(roleId, userId);
	}

	@RequestMapping("/listUser")
	@ResponseBody
	public List<SimpleUserVO> listUser(Long roleId) {
		List<AuthUser> authUsers = authRoleService.listUsers(roleId);
		List<SimpleUserVO> simpleUserVOs = new ArrayList<>(authUsers.size());
		for (AuthUser authUser : authUsers) {
			simpleUserVOs.add(SimpleUserVO.build(authUser));
		}
		return simpleUserVOs;
	}
}