package com.aresky.bookingservice.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final DateTimeFormatter INSTANT_FORMATTER = DateTimeFormatter.ISO_INSTANT;
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static DateTimeFormatter getDateTimeFormatter(String localeString, FormatStyle style){
        Locale locale = Locale.forLanguageTag(localeString);
        return DateTimeFormatter.ofLocalizedDateTime(style).withLocale(locale);
    }

    public static DateTimeFormatter getDateTimeFormatter(Locale locale, FormatStyle style){
        return DateTimeFormatter.ofLocalizedDateTime(style).withLocale(locale);
    }


    public static ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.systemDefault());
    }

    public static ZonedDateTime convertToZonedDateTime(String str) {
        return ZonedDateTime.parse(str, DateTimeFormatter.ISO_INSTANT);
    }

    public static LocalDate convertToLocalDate(String str) {
        return LocalDate.parse(str);
    }

    public static String formatDateTime(ZonedDateTime date) {
        return INSTANT_FORMATTER.format(date);
    }

    public static String formatDateTime(Date date) {
        return INSTANT_FORMATTER.format(date.toInstant());
    }

    public static String formatDate(Date date) {
        return LOCAL_DATE_FORMATTER.format(new java.sql.Date(date.getTime()).toLocalDate());
    }

    public static String formatDate(LocalDate date) {
        return date.format(LOCAL_DATE_FORMATTER);
    }

    public static String format(ZonedDateTime date, DateTimeFormatter formatter) {
        return formatter.format(date);
    }

    public static class CommonLocales {
        public static final Locale VIETNAM = new Locale("vi", "VN");
        public static final Locale US = Locale.US;
        public static final Locale UK = Locale.UK;
        public static final Locale CANADA = Locale.CANADA;
        public static final Locale FRANCE = Locale.FRANCE;
        public static final Locale GERMANY = Locale.GERMANY;
        public static final Locale JAPAN = Locale.JAPAN;
        public static final Locale KOREA = Locale.KOREA;
        public static final Locale CHINA = Locale.CHINA;
        public static final Locale ITALY = Locale.ITALY;
        public static final Locale SPAIN = new Locale("es", "ES");
        public static final Locale BRAZIL = new Locale("pt", "BR");
        public static final Locale INDIA = new Locale("hi", "IN");
        public static final Locale RUSSIA = new Locale("ru", "RU");
    }
}
