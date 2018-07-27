package com.apr7.sponge.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.AuthModule;

@Mapper
public interface AuthRoleModuleMappingDao {
	void saveRoleModuleIds(@Param("roleId") Long roleId, @Param("moduleIds") List<Long> moduleIds);

	void deleteRoleModuleIdsExcept(@Param("roleId") Long roleId, @Param("moduleIds") List<Long> moduleIds);

	List<Long> listModuleIdByRoleIds(@Param("roleIds") List<Long> roleIds);

	List<AuthModule> listModulesByRoleId(@Param("roleId") Long roleId);

	Set<String> listKeysByRoleIds(@Param("roleIds") List<Long> roleIds);
}
