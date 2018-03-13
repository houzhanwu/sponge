package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.PollutantMapping;

@MapperScan
public interface PollutantDao {

	void addPollutant(Pollutant pollutant);

	void addPollutantMapping(PollutantMapping pollutantMapping);

	void deletePollutant(@Param("pollutantId") Long pollutantId);

	void deletePollutantMapping(@Param("pollutantId") Long pollutantId);

	void updatePollutant(Pollutant pollutant);

	void updatePollutantMapping(PollutantMapping pollutantMapping);

	void updateOrderToNegative(@Param("pollutantIds") List<Long> pollutantIds);

	void updateOrder(@Param("pollutantId") Long pollutantId, @Param("order") Integer order);

	List<Pollutant> listPollutant(@Param("show") Boolean show);

	int getMaxOrder();
}
