package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.AuthUser;

@MapperScan
public interface AuthUserRoleMappingDao {
	void addUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

	void deleteUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

	List<Long> listRoleIdByUserId(@Param("userId") Long userId);

	List<AuthUser> listUsers(@Param("roleId") Long roleId);
}
