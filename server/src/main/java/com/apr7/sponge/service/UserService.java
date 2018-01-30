package com.apr7.sponge.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.UserDao;
import com.apr7.sponge.exception.SpongeLoginException;
import com.apr7.sponge.model.User;
import com.apr7.sponge.model.vo.LoginVO;
import com.apr7.sponge.utils.UUIDUtils;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public void updateUserToken(User user) {
		userDao.updateUserToken(user);
	}

	public User getUserById(Long userId) {
		return userDao.getUserById(userId);
	}

	public User getUserByToken(String token) {
		return userDao.getUserByToken(token);
	}

	public LoginVO login(String username, String password) {
		User user = userDao.getUserByLogin(username, DigestUtils.md5Hex(password));
		if (user == null) {
			throw new SpongeLoginException("用户名或密码错误");
		}
		Date now = new Date();
		int expireMins = 30;
		if (now.after(user.getTokenExpire())) {
			user.setToken(UUIDUtils.generateUUID());
			user.setTokenExpire(DateUtils.addMinutes(now, expireMins));
		} else {
			user.setTokenExpire(DateUtils.addMinutes(now, expireMins));
		}
		userDao.updateUserToken(user);
		LoginVO loginVO = new LoginVO();
		loginVO.setToken(user.getToken());
		loginVO.setExpire(TimeUnit.MINUTES.toMillis(expireMins));
		return loginVO;
	}
}
