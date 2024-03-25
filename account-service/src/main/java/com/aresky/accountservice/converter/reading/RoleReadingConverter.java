package com.aresky.accountservice.converter.reading;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import com.aresky.accountservice.model.ERole;

@Component
@ReadingConverter
public class RoleReadingConverter implements Converter<String, ERole> {
    @Override
    public ERole convert(String source) {
        return ERole.valueOf(source);
    }
}
