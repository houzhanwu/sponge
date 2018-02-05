package com.apr7.sponge.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.AuthUserDao;
import com.apr7.sponge.exception.SpongeLoginException;
import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.model.vo.LoginVO;
import com.apr7.sponge.utils.UUIDUtils;

@Service
public class UserService {
	@Autowired
	private AuthUserDao authUserDao;

	public void register(String username, String password) {
		AuthUser authUser = new AuthUser();
		authUser.setUsername(username);
		authUser.setNickname("");
		authUserDao.addUser(authUser, DigestUtils.md5Hex(password));
	}

	public void updateUserToken(AuthUser authUser) {
		authUserDao.updateUserToken(authUser);
	}

	public AuthUser getUserById(Long userId) {
		return authUserDao.getUserById(userId);
	}

	public AuthUser getUserByToken(String token) {
		return authUserDao.getUserByToken(token);
	}

	public Long getUserIdByUsername(String username) {
		return authUserDao.getUserIdByUsername(username);
	}

	public LoginVO login(String username, String password) {
		AuthUser authUser = authUserDao.getUserByLogin(username, DigestUtils.md5Hex(password));
		if (authUser == null) {
			throw new SpongeLoginException("用户名或密码错误");
		}
		Date now = new Date();
		int expireMins = 30;
		if (now.after(authUser.getTokenExpire())) {
			authUser.setToken(UUIDUtils.generateUUID());
			authUser.setTokenExpire(DateUtils.addMinutes(now, expireMins));
		} else {
			authUser.setTokenExpire(DateUtils.addMinutes(now, expireMins));
		}
		authUserDao.updateUserToken(authUser);
		LoginVO loginVO = new LoginVO();
		loginVO.setToken(authUser.getToken());
		loginVO.setExpire(TimeUnit.MINUTES.toMillis(expireMins));
		return loginVO;
	}

	public void logout(String token) {

	}
}
