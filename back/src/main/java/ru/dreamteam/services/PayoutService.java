package ru.dreamteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ru.dreamteam.models.Json;
import ru.dreamteam.utils.HeaderGenerator;
import ru.dreamteam.utils.SignatureGenerator;

import java.util.Random;

@Service
public class PayoutService {

    private final SignatureGenerator signatureGenerator;
    private final HeaderGenerator headerGenerator;

    @Autowired
    public PayoutService(SignatureGenerator signatureGenerator, HeaderGenerator headerGenerator) {
        this.signatureGenerator = signatureGenerator;
        this.headerGenerator = headerGenerator;
    }

    public HttpEntity<?> getEntityForRequest(Json request, Class<? extends Json> clazz){
        Long salt = new Random().nextLong();
        String signature = signatureGenerator.generateSignature(request, salt);

        HttpHeaders httpHeaders = headerGenerator.getRequestHeader(signature, salt);
        return new HttpEntity<>(clazz.cast(request), httpHeaders);
    }
}
