package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.AuthModule;

@MapperScan
public interface AuthModuleDao {
	List<AuthModule> listAllModule();

	List<Long> checkExists(@Param("moduleIds") List<Long> moduleIds);
}
