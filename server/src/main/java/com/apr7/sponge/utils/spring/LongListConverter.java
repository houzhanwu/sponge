package com.apr7.sponge.utils.spring;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.alibaba.fastjson.JSON;

public class LongListConverter implements Converter<String, List<Long>> {

	@Override
	public List<Long> convert(String source) {
		return JSON.parseArray(source, Long.class);
	}
}
