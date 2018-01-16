package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Pollutant;

@MapperScan
public interface PollutantDao {
	List<Pollutant> listPollutant(@Param("show") Boolean show);
}
