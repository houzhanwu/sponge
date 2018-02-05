package com.apr7.sponge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.AuthModuleResourceMappingDao;
import com.apr7.sponge.dao.AuthRoleModuleMappingDao;
import com.apr7.sponge.dao.AuthUserRoleMappingDao;
import com.apr7.sponge.model.AuthResource;

@Service
public class AuthService {

	@Autowired
	private AuthUserRoleMappingDao authUserRoleMappingDao;
	@Autowired
	private AuthRoleModuleMappingDao authRoleModuleMappingDao;
	@Autowired
	private AuthModuleResourceMappingDao authModuleResourceMappingDao;

	public boolean checkAuth(Long userId, String path) {
		List<Long> roleIds = authUserRoleMappingDao.listRoleIdByUserId(userId);
		List<Long> moduleIds = authRoleModuleMappingDao.listModuleIdByRoleIds(roleIds);
		List<AuthResource> resources = authModuleResourceMappingDao.listResourceByModuleIds(moduleIds);
		for (AuthResource resource : resources) {
			if (path.equalsIgnoreCase(resource.getPath())) {
				return true;
			}
		}
		return false;
	}

	public List<String> getAuthEnters(Long userId) {
		List<Long> roleIds = authUserRoleMappingDao.listRoleIdByUserId(userId);
		if (roleIds.isEmpty()) {
			return new ArrayList<>();
		}
		Set<String> keys = authRoleModuleMappingDao.listKeysByRoleIds(roleIds);
		return new ArrayList<>(keys);
	}
}
