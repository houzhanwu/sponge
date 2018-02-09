package com.apr7.sponge.utils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.HmacUtils;

import com.alibaba.fastjson.JSONObject;

public class TokenUtils {
	private static final String SIGN_KEY = "q6m9Wa/]{#=Nz{tj";

	public static String generateToken(String userKey, Long userId, Date expireTime) {
		String uuid = UUIDUtils.generateUUID();
		SortedMap<String, String> sortedMap = new TreeMap<>();
		sortedMap.put("token", uuid);
		sortedMap.put("userId", userId.toString());
		sortedMap.put("createTime", String.valueOf(new Date().getTime()));
		sortedMap.put("expireTime", String.valueOf(expireTime.getTime()));
		JSONObject token = new JSONObject();
		token.putAll(sortedMap);
		token.put("sign", sign(SIGN_KEY + userKey, sortedMap));
		return Base64Utils.encodeURLSafeString(token.toJSONString());
	}

	public static String sign(String key, SortedMap<String, String> sortedMap) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
			sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
		}
		sb.deleteCharAt(sb.length() - 1);
		return HmacUtils.hmacMd5Hex(key, sb.toString());
	}

	public static JSONObject parseToken(String token) {
		String plainText = Base64Utils.decodeString(token);
		JSONObject tokenObject = JSONObject.parseObject(plainText);
		return tokenObject;
	}

	public static boolean checkToken(String userKey, JSONObject token) {
		SortedMap<String, String> sortedMap = new TreeMap<>();
		String sign = null;
		for (String key : token.keySet()) {
			if (key.equals("sign")) {
				sign = token.getString(key);
			} else {
				sortedMap.put(key, token.getString(key));
			}
		}
		return Objects.equals(sign, sign(SIGN_KEY + userKey, sortedMap));
	}
}
