package com.apr7.sponge.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface AuthRoleModuleMappingDao {
	List<Long> listModuleIdByRoleIds(@Param("roleIds") List<Long> roleIds);

	Set<String> listKeysByRoleIds(@Param("roleIds") List<Long> roleIds);
}
