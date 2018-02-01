package com.apr7.sponge.model.vo;

import java.util.List;

import com.apr7.sponge.model.User;

public class UserInfoVO {

	private User user;
	private List<String> enters;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getEnters() {
		return enters;
	}

	public void setEnters(List<String> enters) {
		this.enters = enters;
	}

}
