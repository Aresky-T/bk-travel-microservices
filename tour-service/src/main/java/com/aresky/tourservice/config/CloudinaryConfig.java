package com.aresky.tourservice.config;

import com.cloudinary.Cloudinary;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties("cloudinary")
public class CloudinaryConfig {
    private String cloudName;
    private String apiKey;
    private String apiSecret;
    private Boolean secure;

    private Cloudinary cloudinary;

    @PostConstruct
    public void initCloudinary() {
        Map<String, Object> cloudinaryProps = new HashMap<>();
        cloudinaryProps.put("cloud_name", cloudName);
        cloudinaryProps.put("api_key", apiKey);
        cloudinaryProps.put("api_secret", apiSecret);
        cloudinaryProps.put("secure", secure);

        Properties properties = new Properties();
        properties.putAll(cloudinaryProps);
        cloudinary = new Cloudinary(properties);
    }
}
