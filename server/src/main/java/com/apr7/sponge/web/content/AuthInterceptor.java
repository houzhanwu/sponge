package com.apr7.sponge.web.content;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.apr7.sponge.exception.SpongeAuthException;
import com.apr7.sponge.exception.SpongeNotLoggedInException;
import com.apr7.sponge.model.User;
import com.apr7.sponge.service.AuthService;
import com.apr7.sponge.service.UserService;

public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;

	private Set<String> whitelistsPath;

	public void setWhitelistsPath(Set<String> whitelistsPath) {
		this.whitelistsPath = whitelistsPath;
	}

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
		User user = userService.getUserByToken(token);
		if (user == null) {
			throw new SpongeNotLoggedInException("登陆已失效");
		}
		int expireMins = 30;
		user.setTokenExpire(DateUtils.addMinutes(new Date(), expireMins));
		userService.updateUserToken(user);

		if (!whitelistsPath.contains(request.getRequestURI()) && !authService.checkAuth(user.getId(), request.getRequestURI())) {
			throw new SpongeAuthException("没有该权限");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
