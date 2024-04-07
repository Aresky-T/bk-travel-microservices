package com.aresky.staffservice.converter.reading;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.aresky.staffservice.model.EGender;

@ReadingConverter
public class GenderReadingConverter implements Converter<String, EGender> {

    @Override
    public EGender convert(String source) {
        return EGender.valueOf(source);
    }
}
