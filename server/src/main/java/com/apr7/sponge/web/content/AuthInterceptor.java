package com.apr7.sponge.web.content;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.apr7.sponge.exception.SpongeAuthException;
import com.apr7.sponge.model.User;
import com.apr7.sponge.service.UserService;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				token = cookie.getValue();
				break;
			}
		}
		if (token == null) {
			throw new SpongeAuthException("未登陆");
		}
		User user = userService.getUserByToken(token);
		if (user == null) {
			throw new SpongeAuthException("登陆已失效");
		}
		int expireMins = 30;
		user.setTokenExpire(DateUtils.addMinutes(new Date(), expireMins));
		userService.updateUserToken(user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}
}
