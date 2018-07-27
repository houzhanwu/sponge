package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.AuthRole;

@Mapper
public interface AuthRoleDao {

	void addRole(AuthRole authRole);

	void deleteRole(@Param("roleId") Long roleId);

	void updateRole(AuthRole authRole);

	List<AuthRole> listAllRole();
}
