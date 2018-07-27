package com.apr7.sponge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.apr7.sponge.model.Pollutant;
import com.apr7.sponge.model.PollutantMapping;

@Mapper
public interface PollutantDao {

	void addPollutant(Pollutant pollutant);

	void addPollutantMapping(PollutantMapping pollutantMapping);

	void deletePollutant(@Param("pollutantId") Long pollutantId);

	void deletePollutantMapping(@Param("pollutantId") Long pollutantId);

	void updatePollutant(Pollutant pollutant);

	void updatePollutantMapping(PollutantMapping pollutantMapping);

	void updateOrderToNegative(@Param("pollutantIds") List<Long> pollutantIds);

	void updateOrder(@Param("pollutantId") Long pollutantId, @Param("order") Integer order);

	List<Pollutant> listPollutantByIds(@Param("pollutantIds") List<Long> pollutantIds);

	List<Pollutant> listPollutant(@Param("show") Boolean show);

	int getMaxOrder();

	List<Long> listPollutantIdsByFieldKeys(@Param("fieldName") String fieldName, @Param("fieldKeys") List<String> fieldKeys);
}
