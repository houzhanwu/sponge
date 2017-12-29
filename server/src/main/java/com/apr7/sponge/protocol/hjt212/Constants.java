package com.apr7.sponge.protocol.hjt212;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	private static final Map<String, String> QN_RTN_CODE = new HashMap<>();
	static {
		QN_RTN_CODE.put("1", "准备执行请求");
		QN_RTN_CODE.put("2", "请求被拒绝");
		QN_RTN_CODE.put("3", "PW 错误");
		QN_RTN_CODE.put("4", "MN 错误");
		QN_RTN_CODE.put("5", "ST 错误");
		QN_RTN_CODE.put("6", "Flag 错误");
		QN_RTN_CODE.put("7", "QN 错误");
		QN_RTN_CODE.put("8", "CN 错误");
		QN_RTN_CODE.put("9", "CRC 校验错误");
		QN_RTN_CODE.put("100", "未知错误");
	}

	public class CpParamName {
		public static final String QN_RTN = "QNRTN";
		public static final String EXE_RTN = "EXERTN";
	}
}
