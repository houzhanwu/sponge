package com.apr7.sponge.service;

import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.UserDao;
import com.apr7.sponge.model.User;

@Service
public class UserService {
	private UserDao userDao;

	public User getUserById(Long userId) {
		return this.userDao.getUserById(userId);
	}
}
