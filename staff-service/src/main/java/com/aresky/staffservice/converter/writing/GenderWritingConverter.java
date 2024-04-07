package com.aresky.staffservice.converter.writing;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.aresky.staffservice.model.EGender;

@WritingConverter
public class GenderWritingConverter implements Converter<EGender, String> {

    @Override
    public String convert(EGender source) {
        return source.name();
    }
}
