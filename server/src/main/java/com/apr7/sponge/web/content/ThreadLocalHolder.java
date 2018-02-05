package com.apr7.sponge.web.content;

import com.apr7.sponge.model.AuthUser;

public class ThreadLocalHolder {

	private static ThreadLocal<AuthUser> threadLocalUser = new ThreadLocal<AuthUser>();

	public static AuthUser getUser() {
		return threadLocalUser.get();
	}

	public static void setUser(AuthUser user) {
		threadLocalUser.set(user);
	}

	public static void clearUser() {
		threadLocalUser.remove();
	}
}
