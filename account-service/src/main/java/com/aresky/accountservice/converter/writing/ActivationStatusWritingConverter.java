package com.aresky.accountservice.converter.writing;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import com.aresky.accountservice.model.EActivationStatus;

@Component
@WritingConverter
public class ActivationStatusWritingConverter implements Converter<EActivationStatus, String> {

    @Override
    public String convert(EActivationStatus source) {
        return source.name();
    }
}
