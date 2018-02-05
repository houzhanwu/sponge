package com.apr7.sponge.model.vo;

import org.apache.commons.lang3.StringUtils;

import com.apr7.sponge.model.AuthUser;

public class SimpleUserVO {

	private String username;
	private String nickname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public static SimpleUserVO build(AuthUser authUser) {
		SimpleUserVO simpleUserVO = new SimpleUserVO();
		simpleUserVO.setUsername(authUser.getUsername());
		simpleUserVO.setNickname(StringUtils.defaultIfBlank(authUser.getNickname(), authUser.getUsername()));
		return simpleUserVO;
	}
}
