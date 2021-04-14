package org.example.hrsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class HrSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrSampleApplication.class, args);
    }
}
