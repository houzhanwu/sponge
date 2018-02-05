package com.apr7.sponge.web.content;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.apr7.sponge.exception.SpongeNotLoggedInException;
import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = null;
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.isNotEmpty(cookies)) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					token = cookie.getValue();
					break;
				}
			}
		}
		if (token == null) {
			throw new SpongeNotLoggedInException("未登陆");
		}
		AuthUser authUser = userService.getUserByToken(token);
		if (authUser == null) {
			throw new SpongeNotLoggedInException("登陆已失效");
		}
		int expireMins = 30;
		authUser.setTokenExpire(DateUtils.addMinutes(new Date(), expireMins));
		userService.updateUserToken(authUser);
		ThreadLocalHolder.setUser(authUser);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		ThreadLocalHolder.clearUser();
	}
}
