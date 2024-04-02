package com.aresky.bookingservice.util;

import java.util.Date;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class BookingUtils {
    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public static Date convertToDate(ZonedDateTime time) {
        return Date.from(time.toInstant());
    }
}
