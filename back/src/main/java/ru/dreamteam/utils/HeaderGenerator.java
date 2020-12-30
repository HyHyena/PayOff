package ru.dreamteam.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.dreamteam.properties.PlatformPropertiesHolder;

@Component
public class HeaderGenerator {

    private final PlatformPropertiesHolder propertiesHolder;

    @Autowired
    public HeaderGenerator(PlatformPropertiesHolder propertiesHolder) {
        this.propertiesHolder = propertiesHolder;
    }

    public HttpHeaders getRequestHeader(String signature, Long salt){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Signature", signature);
        httpHeaders.add("X-Partner-Api-Key", propertiesHolder.getXPartnerApiKey());
        httpHeaders.add("X-Salt", salt.toString());
        httpHeaders.add("Content-Type", "application/json");
        return httpHeaders;
    }
}
