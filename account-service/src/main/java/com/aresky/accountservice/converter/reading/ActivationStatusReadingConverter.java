package com.aresky.accountservice.converter.reading;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import com.aresky.accountservice.model.EActivationStatus;

@Component
@ReadingConverter
public class ActivationStatusReadingConverter implements Converter<String, EActivationStatus> {
    @Override
    public EActivationStatus convert(String source) {
        return EActivationStatus.valueOf(source);
    }
}