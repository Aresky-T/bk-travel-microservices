package com.aresky.bookingservice.util;

import java.util.Random;

public class RandomUtils {
    public static long random(int a, int b) {
        if (a > b) {
            return Math.round(Math.random() * (a - b) + b);
        }

        return Math.round(Math.random() * (b - a) + a);
    }

    public static String randomString(int count) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder(count);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            char c = chars.charAt(random.nextInt(chars.length()));
            builder.append(c);
        }

        return builder.toString();
    }
}
