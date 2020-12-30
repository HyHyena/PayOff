package ru.dreamteam.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dreamteam.models.*;
import ru.dreamteam.properties.PlatformEndpointPropertiesHolder;
import ru.dreamteam.properties.PlatformPropertiesHolder;
import ru.dreamteam.services.PayoutService;
import ru.dreamteam.services.RestTemplateCallService;

@RestController
@RequestMapping(value = "payouts-gateway")
@Slf4j
public class PayoutController {

    private final PayoutService payoutService;
    private final RestTemplateCallService restTemplateCallService;

    private final PlatformEndpointPropertiesHolder endpointPropertiesHolder;
    private final PlatformPropertiesHolder propertiesHolder;

    @Autowired

    public PayoutController(PayoutService payoutService,
                            RestTemplateCallService restTemplateCallService,
                            PlatformEndpointPropertiesHolder endpointPropertiesHolder,
                            PlatformPropertiesHolder propertiesHolder) {
        this.payoutService = payoutService;
        this.restTemplateCallService = restTemplateCallService;
        this.endpointPropertiesHolder = endpointPropertiesHolder;
        this.propertiesHolder = propertiesHolder;
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
                .partnerPayoutId(propertiesHolder.getPartnerPayoutId())
                .method("card_ru")
                .accountId(propertiesHolder.getAccountId())
                .build();

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, PayoutRequestToPlatformDTO.class);

        //        How we would normally do
        ResponseEntity<?> responsePayoutEntityResponseEntity =
                restTemplateCallService.postCallAtPlatform(propertiesHolder.getExternalHost() + endpointPropertiesHolder.getPayout(), httpEntity, ResponsePayoutDTO.class);

        return responsePayoutEntityResponseEntity;
    }

    @PostMapping(value = "/balance")
    @ApiOperation(value = "Balance fetching operation", response = ResponseBalanceDTO.class)
    public ResponseEntity<?> balance(@RequestBody RequestBalanceDTO request){
        log.info("Balance info has been requested");

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, RequestBalanceDTO.class);

        ResponseEntity<?> responseEntity =
                restTemplateCallService.postCallAtPlatform(propertiesHolder.getExternalHost() + endpointPropertiesHolder.getBalance(), httpEntity, ResponseBalanceDTO.class);

        return responseEntity;
    }

    @PostMapping(value = "/status")
    @ApiOperation(value = "Status fetching operation", response = ResponseStatusDTO.class)
    public ResponseEntity<?> status(@RequestBody RequestStatusDTO request){
        log.info("Status has been requested");

        HttpEntity<?> httpEntity = payoutService.getEntityForRequest(request, RequestStatusDTO.class);

        ResponseEntity<?> responseEntity =
                restTemplateCallService.postCallAtPlatform(propertiesHolder.getExternalHost() + endpointPropertiesHolder.getBalance(), httpEntity, ResponseStatusDTO.class);
        return responseEntity;
    }

}
