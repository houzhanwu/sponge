package com.apr7.sponge.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.User;

@MapperScan
public interface UserDao {
	void updateUserToken(User user);

	User getUserById(@Param("userId") Long userId);

	User getUserByLogin(@Param("username") String username, @Param("password") String password);
}
