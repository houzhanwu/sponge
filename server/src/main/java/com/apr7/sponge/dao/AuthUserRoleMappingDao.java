package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.AuthUser;

@Mapper
public interface AuthUserRoleMappingDao {
	void addUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

	void deleteUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

	List<Long> listRoleIdByUserId(@Param("userId") Long userId);

	List<AuthUser> listUsers(@Param("roleId") Long roleId);
}
