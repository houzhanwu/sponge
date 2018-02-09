package com.apr7.sponge.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;

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

	public static Date getNextTimePoint(long timePoint) {
		Date now = new Date();
		Date todayZero = DateUtils.truncate(now, Calendar.DATE);
		if (now.getTime() - todayZero.getTime() >= timePoint) {
			return new Date(todayZero.getTime() + TimeUnit.DAYS.toMillis(1) + timePoint);
		} else {
			return new Date(todayZero.getTime() + timePoint);
		}
	}
}
