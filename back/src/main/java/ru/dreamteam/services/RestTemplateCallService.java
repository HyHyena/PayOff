package ru.dreamteam.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.dreamteam.models.Error;

@Service
@Slf4j
public class RestTemplateCallService {

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateCallService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> postCallAtPlatform (String url, HttpEntity<?> request, Class<?> responseType){
        try {
            return restTemplate.postForEntity(url, request, responseType);
        } catch (RestClientException trouble){
            log.error("Your application is having a problem. The event: " + trouble.getLocalizedMessage());
            trouble.printStackTrace();
        }
        return new ResponseEntity<>(Error.builder().message("error occurred").build(), HttpStatus.OK);
    }
}
