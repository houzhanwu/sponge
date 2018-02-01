package com.apr7.sponge.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.User;
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

	@RequestMapping("/login")
	@ResponseBody
	public LoginVO login(String username, String password) {
		LoginVO loginVO = userService.login(username, password);
		return loginVO;
	}

	@RequestMapping("/getInfo")
	@ResponseBody
	public UserInfoVO getInfo(@CookieValue String token) {
		User user = userService.getUserByToken(token);
		user.setNickname(StringUtils.defaultIfBlank(user.getNickname(), user.getUsername()));
		List<String> enters = authService.getAuthEnters(user.getId());
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUser(user);
		userInfoVO.setEnters(enters);
		return userInfoVO;
	}
}