package com.apr7.sponge.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Workshop;

@MapperScan
public interface WorkshopDao {

	Workshop getWorkshop(Long workshopId);

	List<Workshop> listWorkshopByCompanyId(Long companyId);
}
