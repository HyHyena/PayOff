package ru.dreamteam.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dreamteam.models.PayoutRequestToPlatformDTO;

@SpringBootTest
class SignatureGeneratorTest {

    @Autowired
    SignatureGenerator signatureGenerator;

    @Test
    void generateSignature() {
        PayoutRequestToPlatformDTO entity = PayoutRequestToPlatformDTO.builder().amount("20").currency("RUB").method("card").build();
        Long salt = 1923840923842343L;
        String signature = signatureGenerator.generateSignature(entity, salt);
        Assertions.assertEquals("bS1HbhfB+z5gVDfICNuMKruZCMk=", signature);
    }
}

