package ru.dreamteam.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.dreamteam.entities.PayoutEntity;
import ru.dreamteam.entities.RequestBalanceEntity;
import ru.dreamteam.entities.RequestStatusEntity;
import ru.dreamteam.entities.ResponseBalanceEntity;

@RestController
@RequestMapping(value = "payouts-gateway")
@Slf4j
public class PayoutController {

    @PostMapping(value = "/payout")
    public String payout(@RequestBody PayoutEntity payoutEntity){
        log.info(payoutEntity.toString());
        return "response";
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
