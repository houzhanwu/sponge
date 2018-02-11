package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Area;

@MapperScan
public interface AreaDao {
	void addArea(Area area);

	void deleteArea(@Param("areaId") Long areaId);

	void updateArea(Area area);

	List<Area> listAllArea();
}
