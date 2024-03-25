package com.aresky.accountservice.converter.reading;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import com.aresky.accountservice.model.EOnlineStatus;

@Component
@ReadingConverter
public class OnlineStatusReadingConverter implements Converter<String, EOnlineStatus> {
    @Override
    public EOnlineStatus convert(String source) {
        return EOnlineStatus.valueOf(source);
    }
}
