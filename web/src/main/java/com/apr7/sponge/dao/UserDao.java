package com.apr7.sponge.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.User;

@MapperScan
public interface UserDao {
	User getUserById(Long userId);
}
