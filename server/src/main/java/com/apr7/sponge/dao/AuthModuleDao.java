package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.AuthModule;

@Mapper
public interface AuthModuleDao {
	List<AuthModule> listAllModule();

	List<Long> checkExists(@Param("moduleIds") List<Long> moduleIds);
}
