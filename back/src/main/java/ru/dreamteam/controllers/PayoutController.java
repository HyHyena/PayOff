package ru.dreamteam.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
    private String externalHost;

    @Value("${partnerPayoutId}")
    private String partnerPayoutId;

    @Value("${accountId}")
    private Long accountId;

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
                .partnerPayoutId(partnerPayoutId)
                .method("card_ru")
                .accountId(accountId)
                .build();

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, PayoutRequestToPlatformEntity.class);

        RestTemplate restTemplate = new RestTemplate();
//        How we would normally do
        ResponseEntity<ResponsePayoutEntity> responsePayoutEntityResponseEntity = restTemplate.postForEntity(externalHost, httpEntity, ResponsePayoutEntity.class);

        return  responsePayoutEntityResponseEntity;

//        hollow
//        return new ResponseEntity<>(ResponsePayoutEntity.builder().id("1111000022223333")
//                .status("ACCEPTED").build(), HttpStatus.OK);
    }

    @PostMapping(value = "/balance")
    @ApiOperation(value = "Balance fetching operation", response = ResponseBalanceEntity.class)
    public ResponseEntity<?> balance(@RequestBody RequestBalanceEntity request){
        log.info("Balance info has been requested");

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, RequestBalanceEntity.class);

        RestTemplate restTemplate = new RestTemplate();
//        How we would normally do
        ResponseEntity<ResponseBalanceEntity> responseEntity =
                restTemplate.postForEntity(externalHost, httpEntity, ResponseBalanceEntity.class);

        return responseEntity;

//        hollow
//        return new ResponseEntity<>(ResponseBalanceEntity.builder().accountEntity(AccountEntity.builder()
//                .balance(1000000000L).build()).status("ACCEPTED").build(), HttpStatus.OK);
    }

    @PostMapping(value = "/status")
    @ApiOperation(value = "Status fetching operation", response = ResponseStatusEntity.class)
    public ResponseEntity<?> status(@RequestBody RequestStatusEntity request){
        log.info("Status has been requested");

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, RequestStatusEntity.class);

        RestTemplate restTemplate = new RestTemplate();
//        How we would normally do
        ResponseEntity<ResponseStatusEntity> responseEntity =
                restTemplate.postForEntity(externalHost, httpEntity, ResponseStatusEntity.class);
        return responseEntity;

//        hollow
//        return new ResponseEntity<>(ResponseStatusEntity.builder().status("ACCEPTED").build(), HttpStatus.OK);
    }

}
