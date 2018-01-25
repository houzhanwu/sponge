package com.apr7.sponge.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

public class MapUtilsX {

	public static <K> List<K> getKeys(Map<K, ?> map) {
		if (MapUtils.isEmpty(map)) {
			return new ArrayList<>();
		}
		List<K> keys = new ArrayList<>(map.size());
		for (K key : map.keySet()) {
			keys.add(key);
		}
		return keys;
	}
}
