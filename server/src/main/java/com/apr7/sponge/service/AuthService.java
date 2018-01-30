package com.apr7.sponge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.AuthResource;
import com.apr7.sponge.dao.AuthRoleResourceMappingDao;
import com.apr7.sponge.dao.AuthUserRoleMappingDao;
import com.apr7.sponge.model.AuthRole;

@Service
public class AuthService {

	@Autowired
	private AuthUserRoleMappingDao authUserRoleMappingDao;
	@Autowired
	private AuthRoleResourceMappingDao authRoleResourceMappingDao;

	public boolean checkAuth(Long userId, String path) {
		List<AuthRole> roles = authUserRoleMappingDao.listRoleByUserId(userId);
		List<Long> roleIds = new ArrayList<>(roles.size());
		for (AuthRole role : roles) {
			roleIds.add(role.getId());
		}
		List<AuthResource> resources = authRoleResourceMappingDao.listResourceByRoleIds(roleIds);
		for (AuthResource resource : resources) {
			if (path.equalsIgnoreCase(resource.getPath())) {
				return true;
			}
		}
		return false;
	}
}
