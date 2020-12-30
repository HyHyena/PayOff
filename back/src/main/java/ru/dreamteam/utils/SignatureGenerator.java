package ru.dreamteam.utils;

import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.dreamteam.models.Json;
import ru.dreamteam.properties.PlatformPropertiesHolder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Component
public class SignatureGenerator {

    private final PlatformPropertiesHolder propertiesHolder;

    @Autowired
    public SignatureGenerator(PlatformPropertiesHolder propertiesHolder) {
        this.propertiesHolder = propertiesHolder;
    }

    @SneakyThrows
    public String generateSignature(Json body, Long salt){
        SecretKeySpec signingKey =
                new SecretKeySpec(propertiesHolder.getSecret().getBytes(), propertiesHolder.getAlgorithm());
        Mac mac = Mac.getInstance(propertiesHolder.getAlgorithm());
        mac.init(signingKey);
        return Base64.encodeBase64String(mac.doFinal((body.objectToString() + salt).getBytes()));
    }

}
