package ru.dreamteam.utils;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dreamteam.entities.PayoutRequestToPlatformEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignatureGeneratorTest {

    @Autowired
    SignatureGenerator signatureGenerator;

    @Test
    void generateSignature() {
        PayoutRequestToPlatformEntity entity = PayoutRequestToPlatformEntity.builder().amount("20").currency("RUB").method("card").build();
        Long salt = 1923840923842343L;
        String signature = signatureGenerator.generateSignature(entity, salt);
        Assertions.assertEquals("bS1HbhfB+z5gVDfICNuMKruZCMk=", signature);
    }
}

