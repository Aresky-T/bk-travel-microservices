package com.aresky.tourservice.utils;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

public class TourUtils {
    public static String generateTourCode() {
        String prefix = "BK_TRAVEL";
        String str1 = RandomStringUtils.random(20, true, true).toUpperCase().substring(0, 10);
        Date now = new Date(System.currentTimeMillis());
        String str2 = String.valueOf(now.getTime());
        return prefix + "_" + str1 + "_" + str2;
    }

    public static String generateTourCode(String prefix) {
        String str1 = RandomStringUtils.random(20, true, true).toUpperCase().substring(0, 10);
        Date now = new Date(System.currentTimeMillis());
        String str2 = String.valueOf(now.getTime());
        return prefix + "_" + str1 + "_" + str2;
    }

    public static TourParams buildTourParams() {
        return new TourParams();
    }

    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter;
    }

    @Data
    @NoArgsConstructor
    public static class TourParams {
        private final Set<Param> params = new HashSet<>();

        public TourParams bind(String name, Object value) {
            if (value != null) {
                this.params.add(new Param(name, value));
            }
            return this;
        }

        @Data
        @NoArgsConstructor
        public static class Param {
            private String name;
            private Object value;

            public Param(String name, Object value) {
                this.name = name;
                this.value = value;
            }
        }
    }
}
