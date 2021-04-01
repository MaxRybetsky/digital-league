package org.example.hrsample.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HrSampleConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
