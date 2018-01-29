package com.apr7.sponge.model;

import java.util.Date;

public class User {

	private Long id;
	private String username;
	private String nickname;
	private String token;
	private Date tokenExpire;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpire() {
		return tokenExpire;
	}

	public void setTokenExpire(Date tokenExpire) {
		this.tokenExpire = tokenExpire;
	}
}
