package com.aresky.touristattractionservice.utils;

public class StringUtils {
    public static boolean checkStringValueNotNullAndNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
