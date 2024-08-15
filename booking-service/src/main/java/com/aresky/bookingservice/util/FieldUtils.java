package com.aresky.bookingservice.util;

import com.aresky.bookingservice.exception.BookingException;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class FieldUtils {
    public static Field[] findFields(Object obj) {
        try {
            return obj.getClass().getDeclaredFields();
        } catch (SecurityException ex) {
            return null;
        }
    }

    public static Field findField(Object obj, String fieldName) {
        String className = obj.getClass().getSimpleName();
        try {
            return obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException | SecurityException ex) {
            throw new BookingException(className + " field not found: " + fieldName);
        }
    }

    public static Object findFieldValue(Object obj, String fieldName) {
        Field field = findField(obj, fieldName);
        return findFieldValue(obj, field);
    }

    public static Object findFieldValue(Object obj, Field field) {
        try {
            return field.get(obj);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            return null;
        }
    }

    public static void setFieldValue(Object obj, Field field, Object value) {
        if (field != null) {
            field.setAccessible(true);
            String fieldName = field.getName();

            try {
                value = convertFieldValue(field, value);
            } catch (ParseException ex) {
                throw new BookingException("Error converting value for field " + fieldName);
            }

            try {
                field.set(obj, value);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new BookingException("Error setting value for field " + fieldName);
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object convertFieldValue(Field field, Object value) throws ParseException {
        Class<?> fieldType = field.getType();

        if (fieldType.equals(Date.class) && value instanceof String) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse((String) value);
        }

        if (fieldType.equals(LocalDate.class) && value instanceof String) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse((String) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        if (fieldType.equals(LocalDate.class) && value instanceof Date) {
            return ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        if (fieldType.equals(ZonedDateTime.class)) {
            return Instant.parse((String) value).atZone(ZoneId.systemDefault());
        }

        if (fieldType.isEnum()) {
            return Enum.valueOf((Class<? extends Enum>) fieldType, String.valueOf(value));
        }

        return fieldType.cast(value);
    }

    public static void checkFieldsNotNullOrNotEmpty(Field[] fields) {
        if (fields == null || fields.length == 0) {
            throw new BookingException("FormData is empty!");
        }
    }

    public static void checkValueOfStringField(Field field, String value) {
        if (value == null || value.isEmpty()) {
            throw new BookingException("Value of " + field.getName() + " cannot be null or blank!");
        }
    }

    public static void checkValueOfStringField(String fieldName, String value) {
        if (value == null || value.isEmpty()) {
            throw new BookingException("Value of " + fieldName + " cannot be null or blank!");
        }
    }
}
