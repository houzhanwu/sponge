package com.apr7.sponge.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.exception.SpongeException;
import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.model.vo.LoginVO;
import com.apr7.sponge.model.vo.UserInfoVO;
import com.apr7.sponge.service.AuthService;
import com.apr7.sponge.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;

	@RequestMapping("/register")
	@ResponseBody
	public void register(String username, String password) {
		if (userService.getUserIdByUsername(username) != null) {
			throw new SpongeException("该用户名已注册");
		}
		userService.register(username, password);
	}

	@RequestMapping("/login")
	@ResponseBody
	public LoginVO login(String username, String password) {
		LoginVO loginVO = userService.login(username, password);
		return loginVO;
	}

	@RequestMapping("/logout")
	@ResponseBody
	public void logout(@CookieValue String token) {
		userService.logout(token);
	}

	@RequestMapping("/getInfo")
	@ResponseBody
	public UserInfoVO getInfo(@CookieValue String token) {
		AuthUser authUser = userService.getUserByToken(token);
		authUser.setNickname(StringUtils.defaultIfBlank(authUser.getNickname(), authUser.getUsername()));
		List<String> enters = authService.getAuthEnters(authUser.getId());
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUser(authUser);
		userInfoVO.setEnters(enters);
		return userInfoVO;
	}
}