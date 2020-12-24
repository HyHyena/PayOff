package ru.dreamteam.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.dreamteam.entities.*;
import ru.dreamteam.services.PayoutService;

@RestController
@RequestMapping(value = "payouts-gateway")
@Slf4j
public class PayoutController {

    private final PayoutService payoutService;

    @Value("${external_host}")
    String externalHost;

    @Autowired
    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }

    @PostMapping(value = "/payout")
    @ApiOperation(value = "Payout operation", response = ResponsePayoutEntity.class)
    public ResponseEntity<?> payout(@RequestBody PayoutRequestToOurEntity payoutRequestToOurEntity){
        log.info("Payout for amount: " + payoutRequestToOurEntity.getAmount() + " has been requested");

        if (payoutRequestToOurEntity.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        PayoutRequestToPlatformEntity request = PayoutRequestToPlatformEntity.builder()
                .amount(payoutRequestToOurEntity.getAmount())
                .destination(payoutRequestToOurEntity.getCardNumber())
                .build();

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, PayoutRequestToPlatformEntity.class);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponsePayoutEntity> responsePayoutEntityResponseEntity = restTemplate.postForEntity(externalHost, httpEntity, ResponsePayoutEntity.class);

        return responsePayoutEntityResponseEntity;
    }

    @PostMapping(value = "/balance")
    @ApiOperation(value = "Balance fetching operation", response = ResponseBalanceEntity.class)
    public ResponseEntity<?> balance(){
        log.info("Balance info has been requested");

        RequestBalanceEntity request = new RequestBalanceEntity();
        request.setAccountId("");

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, RequestBalanceEntity.class);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseBalanceEntity> responseEntity =
                restTemplate.postForEntity(externalHost, httpEntity, ResponseBalanceEntity.class);

        return responseEntity;
    }

    @PostMapping(value = "/status")
    @ApiOperation(value = "Status fetching operation", response = ResponseStatusEntity.class)
    public ResponseEntity<?> status(){
        log.info("Status has been requested");

        RequestStatusEntity request = new RequestStatusEntity();
        request.setPartnerPayoutId("");

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, RequestStatusEntity.class);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseStatusEntity> responseEntity =
                restTemplate.postForEntity(externalHost, httpEntity, ResponseStatusEntity.class);

        return responseEntity;
    }

}
