package com.apr7.sponge.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.AuthUserDao;
import com.apr7.sponge.exception.SpongeException;
import com.apr7.sponge.exception.SpongeLoginException;
import com.apr7.sponge.model.AuthUser;
import com.apr7.sponge.model.vo.LoginVO;
import com.apr7.sponge.utils.DateUtilsX;
import com.apr7.sponge.utils.MultipageList;
import com.apr7.sponge.utils.SqlUtils;
import com.apr7.sponge.utils.TokenUtils;

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

	public void changePassword(Long userId, String password, String newPassword) {
		if (authUserDao.checkPasswordByUserId(userId, password) == null) {
			throw new SpongeException("原密码不正确");
		}
		authUserDao.updatePassword(userId, newPassword);
	}

	public void updatePassword(Long userId, String newPassword) {
		authUserDao.updatePassword(userId, newPassword);
	}

	public AuthUser getUserById(Long userId) {
		return authUserDao.getUserById(userId);
	}

	public Long getUserIdByUsername(String username) {
		return authUserDao.getUserIdByUsername(username);
	}

	public String getPasswordByUserId(Long userId) {
		return authUserDao.getPasswordByUserId(userId);
	}

	public MultipageList<AuthUser> listUser(String keyword, int page, int size) {
		keyword = SqlUtils.escapeSQLLike(keyword);
		MultipageList<AuthUser> multipageList = new MultipageList<>();
		multipageList.setData(authUserDao.listUser(keyword, (page - 1) * size, size));
		multipageList.setTotal(authUserDao.countUser(keyword));
		return multipageList;
	}

	public LoginVO buildLoginVO(Long userId, String userKey) {
		int expireMins = 30;
		LoginVO loginVO = new LoginVO();
		loginVO.setToken(TokenUtils.generateToken(userKey, userId, DateUtils.addMinutes(new Date(), expireMins)));
		Date refreshTokenExpireTime = DateUtilsX.getNextTimePoint(TimeUnit.HOURS.toMillis(5));
		if (refreshTokenExpireTime.getTime() - new Date().getTime() < TimeUnit.HOURS.toMillis(2)) {
			refreshTokenExpireTime = DateUtils.addDays(refreshTokenExpireTime, 1);
		}
		loginVO.setRefreshToken(TokenUtils.generateToken(userKey, userId, refreshTokenExpireTime));
		return loginVO;
	}

	public LoginVO login(String username, String password) {
		AuthUser authUser = authUserDao.getUserByLogin(username, password);
		if (authUser == null) {
			throw new SpongeLoginException("用户名或密码错误");
		}
		return buildLoginVO(authUser.getId(), password);
	}

	public void logout(String token) {

	}
}
