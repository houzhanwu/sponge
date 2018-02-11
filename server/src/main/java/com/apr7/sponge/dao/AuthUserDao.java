package com.apr7.sponge.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.AuthUser;

@MapperScan
public interface AuthUserDao {
	void addUser(@Param("user") AuthUser authUser, @Param("password") String password);

	void updatePassword(@Param("userId") Long userId, @Param("password") String password);

	AuthUser getUserById(@Param("userId") Long userId);

	AuthUser getUserByLogin(@Param("username") String username, @Param("password") String password);

	String getPasswordByUserId(@Param("userId") Long userId);

	Long checkPasswordByUserId(@Param("userId") Long userId, @Param("password") String password);

	Long getUserIdByUsername(@Param("username") String username);
}
