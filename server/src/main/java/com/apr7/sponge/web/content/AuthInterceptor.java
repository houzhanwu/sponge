package com.apr7.sponge.web.content;

import java.util.Set;

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

	private Set<String> whitelistsPath;

	public void setWhitelistsPath(Set<String> whitelistsPath) {
		this.whitelistsPath = whitelistsPath;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthUser authUser = ThreadLocalHolder.getUser();
		if (!whitelistsPath.contains(request.getRequestURI()) && !authService.checkAuth(authUser.getId(), request.getRequestURI())) {
			throw new SpongeAuthException("没有该权限", request.getRequestURI());
		}
		return true;
	}
}
