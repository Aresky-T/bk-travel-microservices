package com.aresky.accountservice.converter.writing;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import com.aresky.accountservice.model.EOnlineStatus;

@Component
@WritingConverter
public class OnlineStatusWritingConverter implements Converter<EOnlineStatus, String> {

    @Override
    public String convert(EOnlineStatus source) {
        return source.name();
    }
}
