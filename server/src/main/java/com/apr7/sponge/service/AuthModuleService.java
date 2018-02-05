package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.AuthModuleDao;
import com.apr7.sponge.dao.AuthModuleGroupDao;
import com.apr7.sponge.model.AuthModule;
import com.apr7.sponge.model.AuthModuleGroup;

@Service
public class AuthModuleService {
	@Autowired
	private AuthModuleDao authModuleDao;
	@Autowired
	private AuthModuleGroupDao authModuleGroupDao;

	public List<AuthModule> listAllModule() {
		return authModuleDao.listAllModule();
	}

	public List<AuthModuleGroup> listAllModuleGroup() {
		return authModuleGroupDao.listAllModuleGroup();
	}

	public List<Long> checkExists(List<Long> moduleIds) {
		return authModuleDao.checkExists(moduleIds);
	}
}
