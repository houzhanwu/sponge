package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface AuthRoleResourceMappingDao {
	List<AuthResource> listResourceByRoleIds(@Param("roleIds") List<Long> roleIds);
}
