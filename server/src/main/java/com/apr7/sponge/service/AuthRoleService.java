package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apr7.sponge.dao.AuthRoleDao;
import com.apr7.sponge.dao.AuthRoleModuleMappingDao;
import com.apr7.sponge.dao.AuthUserRoleMappingDao;
import com.apr7.sponge.model.AuthModule;
import com.apr7.sponge.model.AuthRole;
import com.apr7.sponge.model.AuthUser;

@Service
public class AuthRoleService {
	@Autowired
	private AuthRoleDao authRoleDao;
	@Autowired
	private AuthRoleModuleMappingDao authRoleModuleMappingDao;
	@Autowired
	private AuthUserRoleMappingDao authUserRoleMappingDao;

	public void addRole(AuthRole authRole) {
		authRoleDao.addRole(authRole);
	}

	@Transactional
	public void deleteRole(Long roleId) {
		authRoleModuleMappingDao.deleteRoleModuleIdsExcept(roleId, null);
		authUserRoleMappingDao.deleteUser(null, roleId);
		authRoleDao.deleteRole(roleId);
	}

	public void updateRole(AuthRole authRole) {
		authRoleDao.updateRole(authRole);
	}

	public List<AuthRole> listAllRole() {
		return authRoleDao.listAllRole();
	}

	@Transactional
	public void updateModules(Long roleId, List<Long> moduleIds) {
		authRoleModuleMappingDao.deleteRoleModuleIdsExcept(roleId, moduleIds);
		authRoleModuleMappingDao.saveRoleModuleIds(roleId, moduleIds);
	}

	public List<AuthModule> listModules(Long roleId) {
		List<AuthModule> modules = authRoleModuleMappingDao.listModulesByRoleId(roleId);
		return modules;
	}

	public void addUser(Long roleId, Long userId) {
		authUserRoleMappingDao.addUser(userId, roleId);
	}

	public void removeUser(Long roleId, Long userId) {
		authUserRoleMappingDao.deleteUser(userId, roleId);
	}

	public List<AuthUser> listUsers(Long roleId) {
		return authUserRoleMappingDao.listUsers(roleId);
	}
}
