package com.apr7.sponge.model.vo;

import java.util.List;

import com.apr7.sponge.model.AuthModule;

public class ModuleGroupVO {

	private String groupName;
	private List<AuthModule> modules;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<AuthModule> getModules() {
		return modules;
	}

	public void setModules(List<AuthModule> modules) {
		this.modules = modules;
	}
}
