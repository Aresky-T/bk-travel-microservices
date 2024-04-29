package com.aresky.accountservice.utils;

public class StringUtils {
    public static boolean checkStringValueNotNullAndNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
