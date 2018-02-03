package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.AuthRole;

@MapperScan
public interface AuthRoleDao {

	void addRole(AuthRole authRole);

	void deleteRole(@Param("roleId") Long roleId);

	void updateRole(AuthRole authRole);

	List<AuthRole> listAllRole();
}
