package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.PollutantMapping;

@MapperScan
public interface PollutantDao {

	public void addPollutant(Pollutant pollutant);

	public void addPollutantMapping(PollutantMapping pollutantMapping);

	public void deletePollutant(@Param("pollutantId") Long pollutantId);

	public void deletePollutantMapping(@Param("pollutantId") Long pollutantId);

	List<Pollutant> listPollutant(@Param("show") Boolean show);

	int getMaxOrder();
}
