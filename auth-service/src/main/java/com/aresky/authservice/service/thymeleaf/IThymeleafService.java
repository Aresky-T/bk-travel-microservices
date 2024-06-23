package com.aresky.authservice.service.thymeleaf;

import java.util.Map;

public interface IThymeleafService {
    String createContent(String template, Map<String, Object> variables);
}
