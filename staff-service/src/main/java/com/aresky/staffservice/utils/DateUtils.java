package com.aresky.staffservice.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static final DateTimeFormatter INSTANT_FORMATTER = DateTimeFormatter.ISO_INSTANT;
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static String formatDateTime(ZonedDateTime date){
        return INSTANT_FORMATTER.format(date);
    }

    public static String formatDateTime(Date date){
        return INSTANT_FORMATTER.format(date.toInstant());
    }

    public static String formatDate(Date date){
        return LOCAL_DATE_FORMATTER.format(new java.sql.Date(date.getTime()).toLocalDate());
    }

    public static String formatDate(LocalDate date){
        return date.format(LOCAL_DATE_FORMATTER);
    }

    public static String format(ZonedDateTime date, DateTimeFormatter formatter){
        return formatter.format(date);
    }

    public static LocalDate toLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
