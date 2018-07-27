package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.apr7.sponge.model.AuthModuleGroup;

@Mapper
public interface AuthModuleGroupDao {
	List<AuthModuleGroup> listAllModuleGroup();
}
