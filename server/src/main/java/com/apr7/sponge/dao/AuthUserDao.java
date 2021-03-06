package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.AuthUser;

@Mapper
public interface AuthUserDao {
	void addUser(@Param("user") AuthUser authUser, @Param("password") String password);

	void updatePassword(@Param("userId") Long userId, @Param("password") String password);

	AuthUser getUserById(@Param("userId") Long userId);

	AuthUser getUserByLogin(@Param("username") String username, @Param("password") String password);

	String getPasswordByUserId(@Param("userId") Long userId);

	Long checkPasswordByUserId(@Param("userId") Long userId, @Param("password") String password);

	Long getUserIdByUsername(@Param("username") String username);

	List<AuthUser> listUser(@Param("keyword") String keyword, @Param("start") int start, @Param("max") int max);

	int countUser(@Param("keyword") String keyword);
}
