package com.apr7.sponge.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtilsX {
	public static Date ceil(Date date, TimeUnit sourceUnit, long sourceDuration) {
		if (date.getTime() % sourceUnit.toMillis(sourceDuration) == 0) {
			return date;
		} else {
			return new Date(floor(date, sourceUnit, sourceDuration).getTime() + sourceUnit.toMillis(sourceDuration));
		}
	}

	public static Date floor(Date date, TimeUnit sourceUnit, long sourceDuration) {
		return new Date(date.getTime() - date.getTime() % sourceUnit.toMillis(sourceDuration));
	}
}
