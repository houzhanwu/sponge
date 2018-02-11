package com.apr7.sponge.utils;

import org.apache.commons.lang3.StringUtils;

public class SqlUtils {

	public static String escapeSQLLike(String input) {
		if (StringUtils.isNotBlank(input)) {
			input = "%" + escapeInput(input) + "%";
			return input;
		}
		return null;
	}

	public static String escapeSQLLikePrefix(String input) {
		if (StringUtils.isNotBlank(input)) {
			input = escapeInput(input) + "%";
			return input;
		}
		return null;
	}

	public static String escapeInput(String input) {
		if (StringUtils.isNotBlank(input)) {
			input = input.replace("\\", "\\\\").replace("_", "\\_").replace("%", "\\%");
		}
		return input;
	}
}
