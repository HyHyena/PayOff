package ru.dreamteam.controllers;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.dreamteam.entities.PayoutRequestToOurEntity;
import ru.dreamteam.entities.PayoutRequestToPlatformEntity;
import ru.dreamteam.entities.ResponsePayoutEntity;
import ru.dreamteam.services.PayoutService;
import ru.dreamteam.utils.HeaderGenerator;
import ru.dreamteam.utils.SignatureGenerator;

import java.util.Random;
import org.springframework.web.client.RestTemplate;
import ru.dreamteam.entities.RequestBalanceEntity;
import ru.dreamteam.entities.RequestStatusEntity;
import ru.dreamteam.entities.ResponseBalanceEntity;

@RestController
@RequestMapping(value = "payouts-gateway")
@Slf4j
public class PayoutController {

    private final PayoutService payoutService;

    @Autowired
    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }

    @PostMapping(value = "/payout")
    public ResponseEntity<?> payout(@RequestBody PayoutRequestToOurEntity payoutRequestToOurEntity){
        log.info(payoutRequestToOurEntity.toString());

        if (payoutRequestToOurEntity.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        PayoutRequestToPlatformEntity request = PayoutRequestToPlatformEntity.builder()
                .amount(payoutRequestToOurEntity.getAmount())
                .destination(payoutRequestToOurEntity.getCardNumber())
                .build();

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, PayoutRequestToPlatformEntity.class);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponsePayoutEntity> responsePayoutEntityResponseEntity = restTemplate.postForEntity("url", httpEntity, ResponsePayoutEntity.class);

        return responsePayoutEntityResponseEntity;
    }

    @PostMapping(value = "/balance")
    public ResponseEntity<?> balance(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Signature", "");
        httpHeaders.add("X-Partner-Api-Key", "");
        httpHeaders.add("X-Salt", "");
        httpHeaders.add("Content-Type", "application/json");

        RequestBalanceEntity requestBalanceEntity = new RequestBalanceEntity();
        requestBalanceEntity.setAccountId("");

        HttpEntity<RequestBalanceEntity> requestEntity =
                new HttpEntity<>(requestBalanceEntity, httpHeaders);

        ResponseEntity<ResponseBalanceEntity> responseEntity =
                restTemplate.postForEntity("", requestEntity, ResponseBalanceEntity.class);

        return responseEntity;
    }

    @PostMapping(value = "/status")
    public ResponseEntity<?> status(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Signature", "");
        httpHeaders.add("X-Partner-Api-Key", "");
        httpHeaders.add("X-Salt", "");
        httpHeaders.add("Content-Type", "application/json");

        RequestStatusEntity requestStatusEntity = new RequestStatusEntity();
        requestStatusEntity.setPartnerPayoutId("");

        HttpEntity<RequestStatusEntity> requestEntity =
                new HttpEntity<>(requestStatusEntity, httpHeaders);

        ResponseEntity<RequestStatusEntity> responseEntity =
                restTemplate.postForEntity("", requestEntity, RequestStatusEntity.class);

        return responseEntity;
    }

}
