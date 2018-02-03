package com.apr7.sponge.web.content;

import com.apr7.sponge.model.User;

public class ThreadLocalHolder {

	private static ThreadLocal<User> threadLocalUser = new ThreadLocal<User>();

	public static User getUser() {
		return threadLocalUser.get();
	}

	public static void setUser(User user) {
		threadLocalUser.set(user);
	}

	public static void clearUser() {
		threadLocalUser.remove();
	}
}
