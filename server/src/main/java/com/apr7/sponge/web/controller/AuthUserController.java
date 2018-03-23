package com.apr7.sponge.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.model.vo.UserVO;
import com.apr7.sponge.service.UserService;
import com.apr7.sponge.utils.MultipageList;

@Controller
@RequestMapping("/user")
public class AuthUserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	@ResponseBody
	public MultipageList<UserVO> listUser(String keyword, int page, int size) {
		MultipageList<AuthUser> authUsers = userService.listUser(keyword, page, size);
		List<UserVO> userVOs = new ArrayList<>(authUsers.getData().size());
		for (AuthUser authUser : authUsers.getData()) {
			userVOs.add(UserVO.build(authUser));
		}
		MultipageList<UserVO> multipageList = new MultipageList<>(userVOs, authUsers.getTotal());
		return multipageList;
	}

	@RequestMapping("/updatePassword")
	@ResponseBody
	public void updatePassword(Long userId, String newPassword) {
		userService.updatePassword(userId, newPassword);
	}
}
