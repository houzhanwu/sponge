package com.apr7.sponge.web.utils;

import com.apr7.sponge.exception.SpongeParamValidationException;

public class ValidateUtils {

	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new SpongeParamValidationException(message);
		}
	}
}
