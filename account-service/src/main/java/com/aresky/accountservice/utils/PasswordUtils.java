package com.aresky.accountservice.utils;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtils {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String ALL = UPPER + LOWER + DIGITS;
    private static final SecureRandom random = new SecureRandom();

    private PasswordUtils() {
    }

    /**
     * The function generates a random password consisting of 10 alphabetic
     * characters.
     * 
     * @return A randomly generated password string of 10 alphabetic characters.
     */
    public static String generateRandomPassword() {
        String randomString = RandomStringUtils.randomAlphabetic(20);
        return randomString.substring(0, 10);
    }

    /**
     * The function generates a random secure password of a specified length.
     * 
     * @param length The length parameter is an integer value that specifies the
     *               length of the random
     *               secure password that the method will generate.
     * @return A randomly generated secure password of the specified length.
     */
    public static String randomSecurePassword(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALL.length());
            sb.append(ALL.charAt(index));
        }
        return sb.toString();
    }
}
