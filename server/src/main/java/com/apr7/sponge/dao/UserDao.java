package com.apr7.sponge.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.User;

@MapperScan
public interface UserDao {
	User getUserById(@Param("userId") Long userId);
}
