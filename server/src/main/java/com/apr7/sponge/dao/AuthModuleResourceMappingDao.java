package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.AuthResource;

@Mapper
public interface AuthModuleResourceMappingDao {
	List<AuthResource> listResourceByModuleIds(@Param("moduleIds") List<Long> moduleIds);
}
