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
import ru.dreamteam.models.*;
import ru.dreamteam.services.PayoutService;

@RestController
@RequestMapping(value = "payouts-gateway")
@Slf4j
public class PayoutController {

    private final PayoutService payoutService;
    private final RestTemplate restTemplate;

    @Value("${external_host}")
    private String externalHost;

    @Value("${partnerPayoutId}")
    private String partnerPayoutId;

    @Value("${accountId}")
    private Long accountId;

    @Value("${endpoint.payout}")
    private String endpointPayout;

    @Value("${endpoint.balance}")
    private String endpointBalance;

    @Value("${endpoint.status}")
    private String endpointStatus;

    @Autowired
    public PayoutController(PayoutService payoutService, RestTemplate restTemplate) {
        this.payoutService = payoutService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/payout")
    @ApiOperation(value = "Payout operation", response = ResponsePayoutDTO.class)
    public ResponseEntity<?> payout(@RequestBody PayoutRequestToOurDTO payoutRequestToOurDTO){
        log.info("Payout for amount: " + payoutRequestToOurDTO.getAmount() + " has been requested");

        if (payoutRequestToOurDTO.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        PayoutRequestToPlatformDTO request = PayoutRequestToPlatformDTO.builder()
                .amount(payoutRequestToOurDTO.getAmount())
                .destination(payoutRequestToOurDTO.getCardNumber())
                .partnerPayoutId(partnerPayoutId)
                .method("card_ru")
                .accountId(accountId)
                .build();

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, PayoutRequestToPlatformDTO.class);

//        How we would normally do
        ResponseEntity<ResponsePayoutDTO> responsePayoutEntityResponseEntity =
                restTemplate.postForEntity(externalHost + endpointPayout, httpEntity, ResponsePayoutDTO.class);

        return  responsePayoutEntityResponseEntity;
    }

    @PostMapping(value = "/balance")
    @ApiOperation(value = "Balance fetching operation", response = ResponseBalanceDTO.class)
    public ResponseEntity<?> balance(@RequestBody RequestBalanceDTO request){
        log.info("Balance info has been requested");

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, RequestBalanceDTO.class);

//        How we would normally do
        ResponseEntity<ResponseBalanceDTO> responseEntity =
                restTemplate.postForEntity(externalHost + endpointBalance, httpEntity, ResponseBalanceDTO.class);

        return responseEntity;
    }

    @PostMapping(value = "/status")
    @ApiOperation(value = "Status fetching operation", response = ResponseStatusDTO.class)
    public ResponseEntity<?> status(@RequestBody RequestStatusDTO request){
        log.info("Status has been requested");

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, RequestStatusDTO.class);

//        How we would normally do
        ResponseEntity<ResponseStatusDTO> responseEntity =
                restTemplate.postForEntity(externalHost + endpointStatus, httpEntity, ResponseStatusDTO.class);
        return responseEntity;
    }

}
