package com.apr7.sponge.model.vo;

import java.util.List;

import com.apr7.sponge.model.AuthUser;

public class UserInfoVO {

	private AuthUser user;
	private List<String> enters;

	public AuthUser getUser() {
		return user;
	}

	public void setUser(AuthUser user) {
		this.user = user;
	}

	public List<String> getEnters() {
		return enters;
	}

	public void setEnters(List<String> enters) {
		this.enters = enters;
	}

}
