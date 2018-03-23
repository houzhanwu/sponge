package com.apr7.sponge.model.vo;

import com.apr7.sponge.model.AuthUser;

public class UserVO {

	private Long id;
	private String username;
	private String nickname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public static UserVO build(AuthUser authUser) {
		UserVO userVO = new UserVO();
		userVO.setId(authUser.getId());
		userVO.setUsername(authUser.getUsername());
		userVO.setNickname(authUser.getNickname());
		return userVO;
	}
}
