package com.apr7.sponge.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.apr7.sponge.exception.SpongeAuthException;
import com.apr7.sponge.exception.SpongeException;
import com.apr7.sponge.exception.SpongeNotLoggedInException;
import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.model.vo.LoginVO;
import com.apr7.sponge.model.vo.UserInfoVO;
import com.apr7.sponge.service.AuthService;
import com.apr7.sponge.service.UserService;
import com.apr7.sponge.utils.TokenUtils;
import com.apr7.sponge.web.content.ThreadLocalHolder;

@Controller
@RequestMapping("/personal")
public class PersonalController {
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

	@RequestMapping("/refreshToken")
	@ResponseBody
	public String refreshToken(String refreshToken) {
		if (refreshToken == null) {
			throw new SpongeNotLoggedInException("未登陆");
		}
		JSONObject tokenObject = TokenUtils.parseToken(refreshToken);
		Long userId = tokenObject.getLong("userId");
		String userKey = userService.getPasswordByUserId(userId);
		if (!TokenUtils.checkToken(userKey, tokenObject)) {
			throw new SpongeAuthException("非法请求");
		}
		Date expireTime = tokenObject.getDate("expireTime");
		if (new Date().after(expireTime)) {
			throw new SpongeNotLoggedInException("登陆已失效");
		}
		int expireMins = 30;
		String token = TokenUtils.generateToken(userKey, userId, DateUtils.addMinutes(new Date(), expireMins));
		return token;
	}

	@RequestMapping("/getInfo")
	@ResponseBody
	public UserInfoVO getInfo() {
		AuthUser authUser = ThreadLocalHolder.getUser();
		authUser.setNickname(StringUtils.defaultIfBlank(authUser.getNickname(), authUser.getUsername()));
		List<String> enters = authService.getAuthEnters(authUser.getId());
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUser(authUser);
		userInfoVO.setEnters(enters);
		return userInfoVO;
	}

	@RequestMapping("/changePassword")
	@ResponseBody
	public LoginVO changePassword(String password, String newPassword) {
		AuthUser authUser = ThreadLocalHolder.getUser();
		userService.changePassword(authUser.getId(), password, newPassword);
		LoginVO loginVO = userService.buildLoginVO(authUser.getId(), newPassword);
		return loginVO;
	}
}