package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.Area;

@Mapper
public interface AreaDao {
	void addArea(Area area);

	void deleteArea(@Param("areaId") Long areaId);

	void updateArea(Area area);

	List<Area> listAllArea();
}
