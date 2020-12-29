package ru.dreamteam.configurations;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public RestTemplate restTemplate() {
        return Mockito.mock(RestTemplate.class, invocationOnMock -> ResponseEntity.ok("{\"mock\" : \"mock\"}"));
    }
}
