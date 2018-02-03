package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.AuthRoleDao;
import com.apr7.sponge.dao.AuthRoleModuleMappingDao;
import com.apr7.sponge.model.AuthModule;
import com.apr7.sponge.model.AuthRole;

@Service
public class AuthRoleService {
	@Autowired
	private AuthRoleDao authRoleDao;
	@Autowired
	private AuthRoleModuleMappingDao authRoleModuleMappingDao;

	public void addRole(AuthRole authRole) {
		authRoleDao.addRole(authRole);
	}

	public void deleteRole(Long roleId) {
		authRoleDao.deleteRole(roleId);
	}

	public void updateRole(AuthRole authRole) {
		authRoleDao.updateRole(authRole);
	}

	public List<AuthRole> listAllRole() {
		return authRoleDao.listAllRole();
	}

	public List<AuthModule> listModules(Long roleId) {
		List<AuthModule> modules = authRoleModuleMappingDao.listModulesByRoleId(roleId);
		return modules;
	}
}
