package com.apr7.sponge.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.apr7.sponge.model.User;
import com.apr7.sponge.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/get")
	@ResponseBody
	public String get(Long userId) {
		User user = this.userService.getUserById(userId);
		return JSON.toJSONString(user);
	}
}