package ru.dreamteam.utils;

import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignatureGenerator {

    static String algorithm = "HmacSHA1";

    @SneakyThrows
    public static String generateSignature(String secret, String body, Long salt){
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(signingKey);
        return Base64.encodeBase64String(mac.doFinal((body+salt).getBytes()));
    }

}
