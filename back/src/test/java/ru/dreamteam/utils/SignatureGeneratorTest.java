package ru.dreamteam.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignatureGeneratorTest {

    @Autowired
    SignatureGenerator signatureGenerator;

    @Test
    void generateSignature() {
        String body = "{\"amount\":20,\"currency\":\"RUB\",\"returnUrl\":\"https://example.com/\",\"method\":\"card\",\"orderId\":123456}";
        String secret = "D4219ACA4320F34497112FDA74A62EB2";
        Long salt = 1923840923842343L;
        String signature = signatureGenerator.generateSignature(secret, body, salt);
        Assertions.assertEquals("8Pf+h8uUf664un/Bhz9jmRoxLkw=", signature);
    }
}

