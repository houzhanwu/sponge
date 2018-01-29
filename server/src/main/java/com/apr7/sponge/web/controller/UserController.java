package com.apr7.sponge.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.vo.LoginVO;
import com.apr7.sponge.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public LoginVO login(String username, String password) {
		LoginVO loginVO = userService.login(username, password);
		return loginVO;
	}

	@RequestMapping("/getInfo")
	@ResponseBody
	public void getInfo(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		System.out.println(cookies);
	}
}