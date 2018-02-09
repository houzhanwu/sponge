package com.apr7.sponge.utils;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {
	private static final String DEFAULT_CHARSET = "UTF-8";

	public static String encodeString(String plainText) {
		byte[] b = plainText.getBytes(Charset.forName(DEFAULT_CHARSET));
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	public static String decodeString(String encodedString) {
		byte[] b = encodedString.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b, Charset.forName(DEFAULT_CHARSET));
		return s;
	}

	public static String encodeURLSafeString(String plainText) {
		return Base64.encodeBase64URLSafeString(plainText.getBytes(Charset.forName(DEFAULT_CHARSET)));
	}
}
