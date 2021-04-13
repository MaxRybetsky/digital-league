package org.example.hrsample.config;

import lombok.Getter;
import lombok.Setter;
import org.example.hrsample.util.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration
//@ConfigurationProperties(prefix = "jwt")
//@EnableConfigurationProperties
//@PropertySource(value = "src\\main\\resources\\application.yaml", factory = YamlPropertySourceFactory.class)
//@Getter
//@Setter
public class YamlJwtProperties {
    private String header;
    private String secret;
    private long expiration;
}
