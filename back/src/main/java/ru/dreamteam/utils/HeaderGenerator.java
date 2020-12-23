package ru.dreamteam.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HeaderGenerator {

    @Value("${XPartnerApiKey}")
    private String XPartnerApiKey;

    public HttpHeaders getRequestHeader(String signature, Long salt){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Signature", signature);
        httpHeaders.add("X-Partner-Api-Key", "???");
        httpHeaders.add("X-Salt", salt.toString());
        httpHeaders.add("Content-Type", "application/json");
        return httpHeaders;
    }
}
