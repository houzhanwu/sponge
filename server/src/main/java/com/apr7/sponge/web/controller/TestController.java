package com.apr7.sponge.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.apr7.sponge.protocol.hjt212.client.command.CN2011ClientCommand;
import com.apr7.sponge.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserService userService;

	@RequestMapping("/test")
	@ResponseBody
	public String get() {
		CN2011ClientCommand cmd = new CN2011ClientCommand();
		try {
			cmd.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "1";
	}
}