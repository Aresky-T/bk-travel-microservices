package com.aresky.accountservice.converter.writing;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import com.aresky.accountservice.model.ERole;

@Component
@WritingConverter
public class RoleWritingConverter implements Converter<ERole, String> {

    @Override
    public String convert(ERole source) {
        return source.name();
    }
}
