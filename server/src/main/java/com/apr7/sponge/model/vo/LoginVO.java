package com.apr7.sponge.model.vo;

public class LoginVO {

	private String token;
	private Long expire;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}
}
