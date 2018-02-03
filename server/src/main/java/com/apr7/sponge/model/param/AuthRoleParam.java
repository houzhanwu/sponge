package com.apr7.sponge.model.param;

import com.apr7.sponge.model.AuthRole;

public class AuthRoleParam {

	private Long id;
	private String name;
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AuthRole toModel() {
		AuthRole authRole = new AuthRole();
		authRole.setId(this.getId());
		authRole.setName(this.getName());
		authRole.setRemarks(this.getRemarks());
		return authRole;
	}
}
