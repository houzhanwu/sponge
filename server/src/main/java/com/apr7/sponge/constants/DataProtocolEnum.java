package com.apr7.sponge.constants;

import java.util.HashMap;
import java.util.Map;

public enum DataProtocolEnum {

	HJT212(1), KNT2014(2);
	private final Integer code;

	public Integer getCode() {
		return code;
	}

	private DataProtocolEnum(Integer code) {
		this.code = code;
	}

	private static Map<Integer, DataProtocolEnum> cache = new HashMap<>();
	static {
		for (DataProtocolEnum dataProtocolEnum : DataProtocolEnum.values()) {
			cache.put(dataProtocolEnum.getCode(), dataProtocolEnum);
		}
	}

	public static DataProtocolEnum fromCode(int code) {
		return cache.get(code);
	}
}
