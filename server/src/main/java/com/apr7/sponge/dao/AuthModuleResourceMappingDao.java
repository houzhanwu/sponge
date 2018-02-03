package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.AuthResource;

@MapperScan
public interface AuthModuleResourceMappingDao {
	List<AuthResource> listResourceByModuleIds(@Param("moduleIds") List<Long> moduleIds);
}
