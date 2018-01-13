package com.apr7.sponge.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.vo.CompanyWorkshopVO;

@MapperScan
public interface WorkshopDao {
	List<CompanyWorkshopVO> listAllCompanyWorkshopVO();
}
