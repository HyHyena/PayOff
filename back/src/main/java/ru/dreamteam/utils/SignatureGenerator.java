package ru.dreamteam.utils;

import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Component
public class SignatureGenerator {

    @Value("${algorithm}")
    String algorithm;

    @SneakyThrows
    public String generateSignature(String secret, String body, Long salt){
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(signingKey);
        return Base64.encodeBase64String(mac.doFinal((body+salt).getBytes()));
    }

}
