package com.apr7.sponge.web.content;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.exception.SpongeAuthException;
import com.apr7.sponge.exception.SpongeNotLoggedInException;
import com.apr7.sponge.exception.SpongeTokenExpireException;
import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.service.UserService;
import com.apr7.sponge.utils.TokenUtils;

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
		JSONObject tokenObject = TokenUtils.parseToken(token);
		Long userId = tokenObject.getLong("userId");
		String userKey = userService.getPasswordByUserId(userId);
		if (!TokenUtils.checkToken(userKey, tokenObject)) {
			throw new SpongeAuthException("非法请求");
		}
		Date expireTime = tokenObject.getDate("expireTime");
		if (new Date().after(expireTime)) {
			throw new SpongeTokenExpireException();
		}
		AuthUser authUser = userService.getUserById(userId);
		ThreadLocalHolder.setUser(authUser);
		MDC.put("ctx_username", authUser.getUsername());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		ThreadLocalHolder.clearUser();
	}
}
