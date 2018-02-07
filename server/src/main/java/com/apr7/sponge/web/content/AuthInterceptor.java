package com.apr7.sponge.web.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.apr7.sponge.exception.SpongeAuthException;
import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.service.AuthService;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AuthService authService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthUser authUser = ThreadLocalHolder.getUser();
		if (!authService.checkAuth(authUser.getId(), request.getRequestURI())) {
			throw new SpongeAuthException("没有该权限", request.getRequestURI());
		}
		return true;
	}
}
