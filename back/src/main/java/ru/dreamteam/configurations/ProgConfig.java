package ru.dreamteam.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("prod")
public class ProgConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
