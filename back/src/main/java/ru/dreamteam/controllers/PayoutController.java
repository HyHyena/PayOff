package ru.dreamteam.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dreamteam.entities.PayoutEntity;

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
    public String balance(){
        return "balance";
    }

    @PostMapping(value = "/status")
    public String status(){
        return "status";
    }

}
