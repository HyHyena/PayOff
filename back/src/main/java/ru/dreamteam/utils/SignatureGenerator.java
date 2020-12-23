package ru.dreamteam.utils;

import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.dreamteam.entities.Json;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Component
public class SignatureGenerator {

    @Value("${algorithm}")
    String algorithm;

    @Value("${secret}")
    String secret;

    @SneakyThrows
    public String generateSignature(Json body, Long salt){
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(signingKey);
        return Base64.encodeBase64String(mac.doFinal((body.objectToString() + salt).getBytes()));
    }

}
